package com.bluu.hdm.rest.entity;


import com.bluu.hdm.rest.vo.inventory.ConnectionVO;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "connections")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConnectionEntity.findAll", query = "SELECT c FROM ConnectionEntity c")
    , @NamedQuery(name = "ConnectionEntity.findById", query = "SELECT c FROM ConnectionEntity c WHERE c.id = :id")
    , @NamedQuery(name = "ConnectionEntity.findByRole", query = "SELECT c FROM ConnectionEntity c WHERE c.role = :role")
    , @NamedQuery(name = "ConnectionEntity.findByTemplate", query = "SELECT c FROM ConnectionEntity c WHERE c.template = :template")
    , @NamedQuery(name = "ConnectionEntity.findByUsercomr", query = "SELECT c FROM ConnectionEntity c WHERE c.usercomr = :usercomr")
    , @NamedQuery(name = "ConnectionEntity.findByPwdcomw", query = "SELECT c FROM ConnectionEntity c WHERE c.pwdcomw = :pwdcomw")
    , @NamedQuery(name = "ConnectionEntity.findByType", query = "SELECT c FROM ConnectionEntity c WHERE c.type = :type")
    , @NamedQuery(name = "ConnectionEntity.findByUrl", query = "SELECT c FROM ConnectionEntity c WHERE c.url = :url")})
public class ConnectionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "role")
    private String role;
    @Size(min = 1, max = 128)
    @Column(name = "template")
    private String template;
    @Size(min = 1, max = 64)
    @Column(name = "usercomr")
    private String usercomr;
    @Size(min = 1, max = 64)
    @Column(name = "pwdcomw")
    private String pwdcomw;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "type")
    private String type;
    @Size(max = 128)
    @Column(name = "url")
    private String url;

    public ConnectionEntity() {
    }

    public ConnectionEntity(ConnectionVO connection) {
        this.id = connection.getId();
        this.role = connection.getRole();
        this.template = connection.getTemplate();
        this.usercomr = connection.getUsercomr();
        this.pwdcomw = connection.getPwdcomw();
        this.type = connection.getType();
        this.url = connection.getUrl();
    }

    public ConnectionEntity(String role, String template, String usercomr, String type, String url) {
        this.role = role;
        this.template = template;
        this.usercomr = usercomr;
        this.type = type;
        this.url = url;
    }

    public void cwmpUpdate(String role, String template, String usercomr, String type, String url) {
        this.role = role;
        this.template = template;
        this.usercomr = usercomr;
        this.type = type;
        this.url = url;
    }

    public void cwmpResetUser(String newUser) {
        this.usercomr = newUser;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getTemplate() {
        return template;
    }

    public String getUsercomr() {
        return usercomr;
    }

    public String getPwdcomw() {
        return pwdcomw;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ConnectionEntity)) {
            return false;
        }
        final ConnectionEntity other = (ConnectionEntity) object;
        return !((id == null && other.id != null) || (id != null && !id.equals(other.id)));
    }
}
