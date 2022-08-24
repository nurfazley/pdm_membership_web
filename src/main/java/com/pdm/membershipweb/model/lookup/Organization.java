package com.pdm.membershipweb.model.lookup;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pdm.membershipweb.model.SystemUser;


@Entity
@Table(name = "Organization")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Organization extends AbstractLookup {

	private static final long serialVersionUID = -7984239509836458922L;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private List<SystemUser> systemUserList = new ArrayList<SystemUser>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private List<Unit> unitList = new ArrayList<Unit>();
	
	public static final String MH = "MUIP HOLDINGS SDN BHD";
	
	public static final String PDM = "MUIP SUPERMARKET GROUP SDN BHD";
	
	public static final String GDM = "GRAND DARULMAKMUR HOTEL KUANTAN";
	
	public static final String PMC = "PAHANG MEDICAL CENTRE SDN BHD";
	
	public static final String DLK = "DARULMAKMUR LODGE KUANTAN";
	
	public static final String MDC = "PUSAT DIALISIS MUIP";
	
	public static final String DHJ = "DARULMAKMUR HOTEL JERANTUT";
	
	public static final String KAA = "KLINIK AL-AMIN SDN BHD";
	
	public static final String DCC = "DARULMAKMUR CHALET CAMERON HIGHLANDS";
	
	public static final String KAF = "KLINIK AL-FARABI SDN BHD";
	
	public static final String PET = "PETRON STATION";
	
	public static final String MPG = "MUIP PLANTATION";
	
	
	public List<SystemUser> getSystemUserList() {
		return systemUserList;
	}

	public void setSystemUserList(List<SystemUser> systemUserList) {
		this.systemUserList = systemUserList;
	}

	public List<Unit> getUnitList() {
		return unitList;
	}

	public void setUnitList(List<Unit> unitList) {
		this.unitList = unitList;
	}
}
