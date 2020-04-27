package br.cesjf.hotellucena.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
    @NamedQuery(name = "Categoria.findByCodigoCategoria", query = "SELECT c FROM Categoria c WHERE c.codigoCategoria = :codigoCategoria"),
    @NamedQuery(name = "Categoria.findByTipoCategoria", query = "SELECT c FROM Categoria c WHERE c.tipoCategoria = :tipoCategoria"),
    @NamedQuery(name = "Categoria.findByValorDiaria", query = "SELECT c FROM Categoria c WHERE c.valorDiaria = :valorDiaria"),
    @NamedQuery(name = "Categoria.findByCapacidade", query = "SELECT c FROM Categoria c WHERE c.capacidade = :capacidade")})
public class Categoria implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriacodigoCategoria")
    private List<Apartamento> apartamentoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoCategoria")
    private Integer codigoCategoria;
    @Basic(optional = false)
    @Column(name = "tipoCategoria")
    private String tipoCategoria;
    @Basic(optional = false)
    @Column(name = "valorDiaria")
    private float valorDiaria;
    @Basic(optional = false)
    @Column(name = "capacidade")
    private int capacidade;

    public Categoria() {
    }

    public Categoria(Integer codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public Categoria(Integer codigoCategoria, String tipoCategoria, float valorDiaria, int capacidade) {
        this.codigoCategoria = codigoCategoria;
        this.tipoCategoria = tipoCategoria;
        this.valorDiaria = valorDiaria;
        this.capacidade = capacidade;
    }

    public Integer getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(Integer codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public String getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(String tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }

    public float getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(float valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCategoria != null ? codigoCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.codigoCategoria == null && other.codigoCategoria != null) || (this.codigoCategoria != null && !this.codigoCategoria.equals(other.codigoCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.cesjf.hotellucena.model.Categoria[ codigoCategoria=" + codigoCategoria + " ]";
    }

    @XmlTransient
    public List<Apartamento> getApartamentoList() {
        return apartamentoList;
    }

    public void setApartamentoList(List<Apartamento> apartamentoList) {
        this.apartamentoList = apartamentoList;
    }
    
}
