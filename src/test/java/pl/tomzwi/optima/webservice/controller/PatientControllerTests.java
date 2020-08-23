package pl.tomzwi.optima.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.tomzwi.optima.webservice.persistance.entity.Patient;
import pl.tomzwi.optima.webservice.persistance.repository.PatientRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PatientControllerTests {

    private static MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private PatientRepository patientRepository;

    // poprawne kody http
    // poprawne rzucanie wyjatkow
    // czy rzucony jest odpowiedni wyjatek gdy nie ma parametrow, errorResponse czy jest poprawny
    // walidacja peselu

    @Before
    public void prepare() {
        if ( mvc == null ) {
            mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        }
    }

    @Test
    public void getPatientPageTest() throws Exception {
        List<Patient> examples = getExamples();
        List<Patient> list0 = examples.subList(0,2);
        List<Patient> list1 = examples.subList(2,4);

        Pageable pageable0 = PageRequest.of(0, 2, Sort.by(Sort.Order.asc("id")));
        Pageable pageable1 = PageRequest.of(1, 2, Sort.by(Sort.Order.asc("id")));

        Page<Patient> page0 = new PageImpl<>(list0, pageable0, examples.size());
        Page<Patient> page1 = new PageImpl<>(list1, pageable1, examples.size());

        Mockito.when(patientRepository.findAll(pageable0)).thenReturn(page0);
        Mockito.when(patientRepository.findAll(pageable1)).thenReturn(page1);

        String content = mvcGetPerform("/api/patients?perPage=2&page=0", 200);
        assertEquals("{\"totalElements\":4,\"totalPages\":2,\"perPage\":2,\"page\":0,\"elements\":[{\"id\":123,\"firstName\":\"Tomasz\",\"lastName\":\"Zwierzynski\",\"personalId\":\"94081602055\",\"address\":\"\",\"sex\":\"MALE\"},{\"id\":124,\"firstName\":\"Adam\",\"lastName\":\"Kowalski\",\"personalId\":\"94071503044\",\"address\":\"\",\"sex\":\"MALE\"}]}",
                content);

        content = mvcGetPerform("/api/patients?perPage=2&page=1", 200);
        assertEquals("{\"totalElements\":4,\"totalPages\":2,\"perPage\":2,\"page\":1,\"elements\":[{\"id\":125,\"firstName\":\"Maria\",\"lastName\":\"Kowalska\",\"personalId\":\"94050678933\",\"address\":\"\",\"sex\":\"FEMALE\"},{\"id\":126,\"firstName\":\"Klaudia\",\"lastName\":\"Wojkiewicz\",\"personalId\":\"94041212345\",\"address\":\"\",\"sex\":\"FEMALE\"}]}",
                content);
    }

    @Test
    public void getPatientPageNoQueryParamsTest() throws Exception {
        mvcGetPerform("/api/patients", 400);
    }

    @Test
    public void getPatientByIdTest() throws Exception {
        List<Patient> examples = getExamples();
        Optional<Patient> patient = examples.stream().findFirst();

        Mockito.when(patientRepository.findById(123L)).thenReturn(patient);

        String content = mvcGetPerform("/api/patients/123", 200);
        assertEquals("{\"id\":123,\"firstName\":\"Tomasz\",\"lastName\":\"Zwierzynski\",\"personalId\":\"94081602055\",\"address\":\"\",\"sex\":\"MALE\"}",
                content);
    }

    @Test
    public void getPatientByIdNotFoundTest() throws Exception {
        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.empty());
        mvcGetPerform("/api/patients/1", 404);
    }

    @Test
    public void searchByNameFoundTest() throws Exception {
        List<Patient> examples = getExamples();
        List<Patient> searched = examples.stream().filter(p -> p.getLastName().startsWith("Kowal")).collect(Collectors.toList());

        Mockito.when(patientRepository.findByFirstNameLastNameSearch("Kowal")).thenReturn(searched);

        String content = mvcGetPerform("/api/patients/searchname?text=Kowal", 200);
        assertEquals("[{\"id\":124,\"firstName\":\"Adam\",\"lastName\":\"Kowalski\",\"personalId\":\"94071503044\",\"address\":\"\",\"sex\":\"MALE\"},{\"id\":125,\"firstName\":\"Maria\",\"lastName\":\"Kowalska\",\"personalId\":\"94050678933\",\"address\":\"\",\"sex\":\"FEMALE\"}]",
                content);
    }

    @Test
    public void searchByNameNotFoundTest() throws Exception {
        Mockito.when(patientRepository.findByFirstNameLastNameSearch("blah")).thenReturn(Collections.emptyList());
        String content = mvcGetPerform("/api/patients/searchname?text=blah", 200);
        assertEquals("[]", content);
    }

    @Test
    public void searchByNameTooShortTest() throws Exception {
        mvcGetPerform("/api/patients/searchname?text=bl", 400);
    }

    @Test
    public void searchByPersonalIdFoundTest() throws Exception {
        List<Patient> examples = getExamples();
        List<Patient> searched = examples.stream().filter(p -> p.getPersonalIdentityNumber().startsWith("9404")).collect(Collectors.toList());

        Mockito.when(patientRepository.findByPersonalIdSearch("9404")).thenReturn(searched);

        String content = mvcGetPerform("/api/patients/searchid?text=9404", 200);
        assertEquals("[{\"id\":126,\"firstName\":\"Klaudia\",\"lastName\":\"Wojkiewicz\",\"personalId\":\"94041212345\",\"address\":\"\",\"sex\":\"FEMALE\"}]",
                content);
    }

    @Test
    public void searchByPersonalIdNotFoundTest() throws Exception {
        Mockito.when(patientRepository.findByPersonalIdSearch("0000")).thenReturn(Collections.emptyList());
        String content = mvcGetPerform("/api/patients/searchid?text=0000", 200);
        assertEquals("[]", content);
    }

    @Test
    public void searchByPersonalIdTooShortTest() throws Exception {
        mvcGetPerform("/api/patients/searchid?text=94", 400);
    }

    @Test
    public void addPatientTest() throws Exception {
        Patient patient = newPatient(1L, "Test", "Fest", "10203004056", "", Patient.Sex.MALE);
        Mockito.when(patientRepository.save(any(Patient.class))).then(a -> {
            Patient argument = a.getArgument(0, Patient.class);
            argument.setId(1L);
            return patient;
        });
        String content = mvcPostPerform("/api/patients", "{\"firstName\": \"Test\",\"lastName\": \"Fest\",\"personalId\": \"94081603044\",\"sex\": \"MALE\"}", 201);
        assertEquals("{\"id\":1,\"firstName\":\"Test\",\"lastName\":\"Fest\",\"personalId\":\"94081603044\",\"address\":null,\"sex\":\"MALE\"}", content);
    }

    @Test
    public void addPatientDuplicatePersonalIdTest() throws Exception {
        // TODO
    }

    @Test
    public void addPatientBadRequestTest() throws Exception {
        mvcPostPerform("/api/patients", "{\"firstName\": \"Test\",\"personalId\": \"94081603044\",\"sex\": \"MALE\"}", 400);
        mvcPostPerform("/api/patients", "{\"firstName\": null,\"lastName\": \"Fest\",\"personalId\": \"94081603044\",\"sex\": \"MALE\"}", 400);
        mvcPostPerform("/api/patients", "{\"firstName\": \"Test\",\"lastName\": \"Fest\",\"sex\": \"MALE\"}", 400);
        mvcPostPerform("/api/patients", "{\"firstName\": \"Test\",\"lastName\": \"Fest\",\"personalId\": \"94081603044\"}", 400);
    }

    private String mvcGetPerform(String uri, int expectedStatus) throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(expectedStatus, mvcResult.getResponse().getStatus());

        return mvcResult.getResponse().getContentAsString();
    }

    private String mvcPostPerform(String uri, String content, int expectedStatus) throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(expectedStatus, mvcResult.getResponse().getStatus());

        return mvcResult.getResponse().getContentAsString();
    }

    private List<Patient> getExamples() {
        List<Patient> result = new ArrayList<>();
        result.add(newPatient(123L, "Tomasz", "Zwierzynski", "94081602055", "", Patient.Sex.MALE));
        result.add(newPatient(124L, "Adam", "Kowalski", "94071503044", "", Patient.Sex.MALE));
        result.add(newPatient(125L, "Maria", "Kowalska", "94050678933", "", Patient.Sex.FEMALE));
        result.add(newPatient(126L, "Klaudia", "Wojkiewicz", "94041212345", "", Patient.Sex.FEMALE));

        return result;
    }

    private Patient newPatient(long id, String firstName, String lastName, String personalId, String address, Patient.Sex sex) {
        Patient patient = new Patient();
        patient.setId(id);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPersonalIdentityNumber(personalId);
        patient.setAddress(address);
        patient.setSex(sex);

        return patient;
    }

}
