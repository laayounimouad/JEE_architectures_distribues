### ajouter des enregistrement / add-update :
- exemple:

        @Autowired
        private PatientRepository patientRepository;

##### in run method :
        patientRepository.save(
                new Patient(null,"hassan","mlsdkf@mail",false,34));
``
si l'object a un id déja existant il va faire un update sur l'enregistrement sinon il va creer un nouveau enregistrement
``
### get all 
  - findAll :

 
    List<Patient> patients = patientRepository.findAll();
        patients.forEach(patient -> {
            System.out.println(patient.getName());
        });
  
 - findById

    
    Patient patient = patientRepository.findById(1L).orElse(null);
        if (patient != null) System.out.println(patient);

