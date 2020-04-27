package br.cesjf.hotellucena.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Apartamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apartamento.findAll", query = "SELECT a FROM Apartamento a"),
    @NamedQuery(name = "Apartamento.findByIdApartamento", query = "SELECT a FROM Apartamento a WHERE a.idApartamento = :idApartamento"),
    @NamedQuery(name = "Apartamento.findByCodigoApartamento", query = "SELECT a FROM Apartamento a WHERE a.codigoApartamento = :codigoApartamento")})
public class Apartamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idApartamento")
    private Integer idApartamento;
    @Basic(optional = false)
    @Column(name = "codigoApartamento")
    private int codigoApartamento;
    @JoinColumn(name = "categoria_codigoCategoria", referencedColumnName = "codigoCategoria")
    @ManyToOne(optional = false)
    private Categoria categoriacodigoCategoria;

    public Apartamento() {
    }

    public Apartamento(Integer idApartamento) {
        this.idApartamento = idApartamento;
    }

    public Apartamento(Integer idApartamento, int codigoApartamento) {
        this.idApartamento = idApartamento;
        this.codigoApartamento = codigoApartamento;
    }

    public Integer getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(Integer idApartamento) {
        this.idApartamento = idApartamento;
    }

    public int getCodigoApartamento() {
        return codigoApartamento;
    }

    public void setCodigoApartamento(int codigoApartamento) {
        this.codigoApartamento = codigoApartamento;
    }

    public Categoria getCategoriacodigoCategoria() {
        return categoriacodigoCategoria;
    }

    public void setCategoriacodigoCategoria(Categoria categoriacodigoCategoria) {
        this.categoriacodigoCategoria = categoriacodigoCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idApartamento != null ? idApartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apartamento)) {
            return false;
        }
        Apartamento other = (Apartamento) object;
        if ((this.idApartamento == null && other.idApartamento != null) || (this.idApartamento != null && !this.idApartamento.equals(other.idApartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.cesjf.hotellucena.Apartamento[ idApartamento=" + idApartamento + " ]";
    }
    
}
