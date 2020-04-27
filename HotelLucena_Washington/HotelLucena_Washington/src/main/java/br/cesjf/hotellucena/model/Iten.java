package br.cesjf.hotellucena.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "itens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itens.findAll", query = "SELECT u FROM Itens u"),
    @NamedQuery(name = "Itens.findByIdItem", query = "SELECT u FROM Itens u WHERE u.idItem = :idItem"),
    @NamedQuery(name = "Itens.findByNomeItem", query = "SELECT u FROM Itens u WHERE u.nomeItem = :nomeItem "),
    @NamedQuery(name = "Itens.findByDescricaoItem", query = "SELECT u FROM Itens u WHERE u.descricaoItem = :descricaoItem"),
    @NamedQuery(name = "Itens.findByValorItem", query = "SELECT u FROM Itens u WHERE u.valorItem = :valorItem")})

public class Iten implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Basic(optional = false)
    @Column(name = "idItem")
    private Integer idItem;
    
    @Basic(optional = false)
    @Column(name = "nomeItem")
    private String nomeItem;
    
    @Basic(optional = false)
    @Column(name = "descricaoItem")
    private String descricaoItem;
    
    @Basic(optional = false)
    @Column(name = "valorItem")
    private Float valorItem;
    

    public Iten() {
    }

    public Iten(Integer idItem) {
        this.idItem = idItem;
    }

    public Iten(Integer idItem, String nomeItem, String descricaoItem, Float valorItem) {
        this.idItem = idItem;
        this.nomeItem = nomeItem;
        this.descricaoItem = descricaoItem;
        this.valorItem = valorItem;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public Float getvalorItem() {
        return valorItem;
    }

    public void setvalorItem(Float valorItem) {
        this.valorItem = valorItem;
    }
    
@Override
    public int hashCode() {
        int hash = 0;
        hash += (idItem != null ? idItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Iten)) {
            return false;
        }
        Iten other = (Iten) object;
        if ((this.idItem == null && other.idItem != null) || (this.idItem != null && !this.idItem.equals(other.idItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.cesjf.hotellucena.model.Itens[ idItem=" + idItem + " ]";
    }
  



    
}
