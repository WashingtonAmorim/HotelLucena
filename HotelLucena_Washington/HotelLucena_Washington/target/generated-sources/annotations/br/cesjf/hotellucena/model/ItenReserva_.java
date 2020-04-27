package br.cesjf.hotellucena.model;

import br.cesjf.hotellucena.model.Iten;
import br.cesjf.hotellucena.model.Reserva;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-23T08:16:20")
@StaticMetamodel(ItenReserva.class)
public class ItenReserva_ { 

    public static volatile SingularAttribute<ItenReserva, Reserva> reservascodigoReserva;
    public static volatile SingularAttribute<ItenReserva, Integer> quantidadeItem;
    public static volatile SingularAttribute<ItenReserva, Iten> itensidItem;

}