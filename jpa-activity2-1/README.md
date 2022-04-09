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

- ajouter des methodes dans les repositories:
        
          public List<Patient> findByMalade(boolean a);
- pagination :
  - dans le repository:
            
        public Page<Patient> findByMalade(boolean a, Pageable pageable);
  - implementation :
            
        Page<Patient> patients = patientRepository.findByMalade(true, PageRequest.of(0,4));  
- Autres exemples pour des fonctions de repositories:

        List<Patient> findByMaladeAndScoreIsLessThan(boolean a,int score);
        List<Patient> findByDateNaissanceBetween(Date d1, Date d2);
- Pour eviter d'avoir des fonctions trop grande:
  - ajouter l'annotation Query (en HQL)


    @Query("Select p from Patient p where p.dateNaissance between :x and :y or p.nom like :z")
    List<Patient> chercherPatients(@Param("x") Date d1,@Param("y") Date d2,Param("z") String nom);

    @Query("Select p from Patient p where p.name like :x and p.score<:y")
    List<Patient> chercherPatients(@Param("x") String nom,@Param("y") int scoreMin);
  - implementation :
 
    
    List<Patient> patientList=patientRepository.chercherPatients("%h%",40);

#####  afficher les requetes SQL en temps d'execution :
- ajouter au ficher properties :
        
      spring.jpa.show-sql=true

### utilisation de MySQL :
     