package br.cesjf.hotellucena.model;

import br.cesjf.hotellucena.model.Apartamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-23T08:16:20")
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile SingularAttribute<Categoria, String> tipoCategoria;
    public static volatile SingularAttribute<Categoria, Float> valorDiaria;
    public static volatile SingularAttribute<Categoria, Integer> codigoCategoria;
    public static volatile SingularAttribute<Categoria, Integer> capacidade;
    public static volatile ListAttribute<Categoria, Apartamento> apartamentoList;

}