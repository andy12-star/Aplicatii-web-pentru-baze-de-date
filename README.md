Cerințele proiectului I:
Aplicația va îndeplini cerințele următoare.
1) Vor fi create relații între entități de toate tipurile: @OneToOne, @OneToMany,
@ManyToOne, @ManyToMany.
2) Vor fi implementate toate tipurile de operații CRUD prin intermediul .
3) Se va testa aplicația folosindu-se profiluri și două baze de date diferite, una dintre ele
pentru etapa de testare și alta pentru dezvoltare/producție. Se poate utiliza și o bază de date
in-memory (H2).
4) Utilizare unit-tests/integration tests.
5) Se vor include view-uri. Se vor valida datele din formulare, se vor trata excepțiile.
6) Se vor utiliza log-uri. Opțional aspecte.
7) Vor fi utilizate opțiuni de paginare și sortare a datelor.
8) Se va include Spring Security (cerința minima autentificare jdbc).

Cerințele proiectului II:
Migrarea proiectului I la o arhitectură cu micro-servicii (Spring Cloud sau K8s sau soluții
mixte).
1) Configurarea unitară a micro-serviciilor.
2) Asigurarea comunicării între micro-servicii, service discovery funcțional.
3) Demonstrare scalabilitate și load-balancing.
4) Monitorizare, metrici și servicii de logging.
5) Elemente de securitate.
6) Asigurare reziliență, servicii disponibile în caz de erori.
7) Utilizare de design-patterns specifice.
