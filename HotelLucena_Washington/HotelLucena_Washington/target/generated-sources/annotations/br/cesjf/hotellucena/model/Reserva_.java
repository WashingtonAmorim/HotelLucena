package br.cesjf.hotellucena.model;

import br.cesjf.hotellucena.model.Apartamento;
import br.cesjf.hotellucena.model.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-23T08:16:20")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, Integer> codigoReserva;
    public static volatile SingularAttribute<Reserva, Integer> numeroHospedes;
    public static volatile SingularAttribute<Reserva, Date> dataEntrada;
    public static volatile SingularAttribute<Reserva, Usuario> usuarioscodUsuario;
    public static volatile SingularAttribute<Reserva, Double> valorPago;
    public static volatile SingularAttribute<Reserva, Date> dataSaida;
    public static volatile SingularAttribute<Reserva, Apartamento> apartamentocodigoApartamento;
    public static volatile SingularAttribute<Reserva, String> status;

}