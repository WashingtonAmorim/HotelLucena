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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuarios.findByCodUsuario", query = "SELECT u FROM Usuarios u WHERE u.codUsuario = :codUsuario"),
    @NamedQuery(name = "Usuarios.findByNomeUsuario", query = "SELECT u FROM Usuarios u WHERE u.nomeUsuario = :nomeUsuario"),
    @NamedQuery(name = "Usuarios.findByContatoUsuario", query = "SELECT u FROM Usuarios u WHERE u.contatoUsuario = :contatoUsuario"),
    @NamedQuery(name = "Usuarios.findByTipoUsuariol", query = "SELECT u FROM Usuarios u WHERE u.tipoUsuariol = :tipoUsuariol"),
    @NamedQuery(name = "Usuarios.findByEmailUsuario", query = "SELECT u FROM Usuarios u WHERE u.emailUsuario = :emailUsuario")})

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "codUsuario")
    private int codUsuario;
    @Basic(optional = false)
    @Column(name = "nomeUsuario")
    private String nomeUsuario;
    @Basic(optional = false)
    @Column(name = "contatoUsuario")
    private String contatoUsuario;
    @Basic(optional = false)
    @Column(name = "emailUsuario")
    private String emailUsuario;
    @Basic(optional = false)
    @Column(name = "tipoUsuariol")
    private String tipoUsuariol;
   

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, int codUsuario, String nomeUsuario, String contatoUsuario,String emailUsuario,String tipoUsuariol) {
        this.idUsuario = idUsuario;
        this.codUsuario = codUsuario;
        this.nomeUsuario = nomeUsuario;
        this.contatoUsuario = contatoUsuario;
        this.emailUsuario = emailUsuario;
        this.tipoUsuariol = tipoUsuariol;
        
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getContatoUsuario() {
        return contatoUsuario;
    }

    public void setContatoUsuario(String contatoUsuario) {
        this.contatoUsuario = contatoUsuario;
    }
    
      public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
    
    
    public String getTipoUsuariol() {
        return tipoUsuariol;
    }

    public void setTipoUsuariol(String tipoUsuariol) {
        this.tipoUsuariol = tipoUsuariol;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.cesjf.hotellucena.model.Usuarios[ idUsuario=" + idUsuario + " ]";
    }
 
    
}
