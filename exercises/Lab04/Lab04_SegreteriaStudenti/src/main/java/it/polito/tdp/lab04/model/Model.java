package it.polito.tdp.lab04.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

    CorsoDAO corsoDAO = new CorsoDAO();
    StudenteDAO studenteDAO = new StudenteDAO();
    Map<String, Corso> corsi;

    public Model() {
        corsi = new HashMap<>();
        for (Corso c : this.getListaCorsi()) {
            corsi.put(c.toString(), c);
        }
    }

    public List<Corso> getListaCorsi() {

        return corsoDAO.getTuttiICorsi();
    }

    public Studente getStudenteDaMatricola(Integer matricola) {
        return studenteDAO.getStudenteDaMatricola(matricola);
    }

    public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
        return corsoDAO.getStudentiIscrittiAlCorso(corso);
    }

    public List<Corso> getCorsiStudente(Studente stud) {

        return studenteDAO.getCorsiStudente(stud);
    }

    public boolean isStudenteIscrittoACorso(Studente stud, Corso corso) {
        return this.getCorsiStudente(stud).contains(corso);
    }

    public boolean iscriviStudenteACorso(Studente stud, Corso corso) {
        return corsoDAO.inscriviStudenteACorso(stud, corso);
    }
}
