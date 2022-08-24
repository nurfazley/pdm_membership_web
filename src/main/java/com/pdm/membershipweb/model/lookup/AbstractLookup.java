package com.pdm.membershipweb.model.lookup;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractLookup implements Serializable {
	
	private static final long serialVersionUID = 2355143865058044058L;

	@Id
	@SequenceGenerator(name = "lookupSequence", sequenceName = "lookup_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lookupSequence")
	private Long id;
	
	@Column(name = "code", unique = true)
	private String code;
	
	@Column(name = "textString")
	private String textString;

	@Column(name = "description")
	private String description;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTextString() {
		return textString;
	}

	public void setTextString(String textString) {
		this.textString = textString;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		if (getId() != null) {
			return getId().hashCode();
		}
		
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
	
		if (obj == null) {
			return false;
		}
	
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		AbstractLookup other = (AbstractLookup) obj;
		
		if (getId() == null || other.getId() == null) {
			return false;
		}
		
		return getId().equals(other.getId());
	}
}
