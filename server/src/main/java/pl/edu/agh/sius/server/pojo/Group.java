package pl.edu.agh.sius.server.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Group")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name="\"group\"")
public class Group implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@XmlID
	@Id
	@XmlElement	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private String id;
	
	@XmlElement
	@Column(columnDefinition="nvarchar(100)", nullable=false)
	private String name;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinTable (name = "member_group",
		joinColumns = { @JoinColumn(name="group_id") },
		inverseJoinColumns = { @JoinColumn(name="user_id") })
	private List<User> memberUsers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
