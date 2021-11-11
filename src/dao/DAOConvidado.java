package dao;

import com.db4o.query.Query;
import modelo.Convidado;

import java.util.List;

public class DAOConvidado extends DAO<Convidado>{

    public Convidado read(Object chave){
        String nome = (String) chave;
        Query q = manager.query();
        q.constrain(Convidado.class);
        q.descend("nome").constrain(nome);
        List<Convidado> resultados = q.execute();
        if(resultados.size()>0)
            return resultados.get(0);
        else
            return null;
    }

}
