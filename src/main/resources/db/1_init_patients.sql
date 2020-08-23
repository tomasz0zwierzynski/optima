DROP TABLE IF EXISTS public.patients;
DROP TABLE IF EXISTS public.appointments;

CREATE TABLE public.patients
(
    id bigserial NOT NULL,
    firstname character varying NOT NULL,
    lastname character varying NOT NULL,
    personalidentitynumber character varying NOT NULL,
    sex character varying NOT NULL,
    address character varying,
    PRIMARY KEY (id)
);

ALTER TABLE public.patients
    OWNER to postgres;

CREATE TABLE public.appointments
(
    id bigserial NOT NULL,
    patientid bigint NOT NULL,
    appointmentdate timestamp with time zone NOT NULL,
    description character varying NOT NULL,
    note character varying,
    PRIMARY KEY (id)
);

ALTER TABLE public.appointments
    OWNER to postgres;

ALTER TABLE public.appointments
    ADD CONSTRAINT "FK_patientid" FOREIGN KEY (patientid)
    REFERENCES public.patients (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
CREATE INDEX "fki_FK_patientid"
    ON public.appointments(patientid);