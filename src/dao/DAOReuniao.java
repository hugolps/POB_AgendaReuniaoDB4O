package dao;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;
import modelo.Convidado;
import modelo.Participante;
import modelo.Reuniao;

import java.util.ConcurrentModificationException;
import java.util.List;

public class DAOReuniao extends DAO<Reuniao> {

    public Reuniao read (Object chive){
        int id = (Integer) chive;

        Query q = manager.query();
        q.constrain(Reuniao.class);
        q.descend("id").constrain(id);
        List<Reuniao> resultados = q.execute();
        if (resultados.size()>0)
            return resultados.get(0);
        else
            return null;

    }

    public void create(Reuniao obj){
        Reuniao r = (Reuniao) obj;
        int id = super.getMaxId();
        id++;
        r.setId(id);
        manager.store(r);
    }

    public List<Reuniao> readByAssunto(String assunto){
        Query q = manager.query();
        q.constrain(Reuniao.class);
        q.descend("assunto").constrain(assunto).like();
        List<Reuniao> result  = q.execute();
        return result;
    }

    public List<Reuniao> readByParticipante(String nome){
        Query q = manager.query();
        q.constrain(Reuniao.class);
        q.descend("participante").descend("nome").constrain(nome);
        List<Reuniao> result = q.execute();
        return result;
    }

    public List<Reuniao> reuniaoContemConvidado(){
        Query q = manager.query();
        q.constrain(Reuniao.class);
        q.constrain(new Filtro());
        List<Reuniao> result = q.execute();
        return result;
    }

}
/*------------------------------------------------------*/
class Filtro implements Evaluation {
    public void evaluate(Candidate candidate) {
        Reuniao r = (Reuniao) candidate.getObject();
        boolean resposta = false;
        for (Participante participante : r.getParticipantes())
            if(participante instanceof Convidado) {
                resposta = true;}

        candidate.include(resposta);
    }
}

