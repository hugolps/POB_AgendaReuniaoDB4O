package dao;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;
import modelo.Convidado;
import modelo.Participante;
import modelo.Reuniao;

import javax.mail.Part;
import java.util.List;

public class DAOParticipante extends DAO<Participante> {

    public Participante read(Object chave){
        String nome = (String) chave;
        Query q = manager.query();
        q.constrain(Participante.class);
        q.descend("nome").constrain(nome);
        List<Participante> resultados = q.execute();
        if(resultados.size()>0)
            return resultados.get(0);
        else
            return null;
    }

    public List<Participante> encontrarParticipantes(String nome, int mes){
        Query q = manager.query();
        q.constrain(Participante.class);
        q.descend("Reuniao").descend("participantes").descend("participante").descend("nome").constrain(nome);
        //q.descend("Reuniao").descend("datahora").constrain("/"+mes+"/").like();
        //q.descend("participante").descend("reunioes").descend("participantes").descend("nome").constrain(nome).like();
        //q.descend("participante").descend("reunioes").descend("datahora").constrain("/${mes}+").like();
        List<Participante> result = q.execute();
        return result;
    }
}

