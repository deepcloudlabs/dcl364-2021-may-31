package com.example.crm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
<datasource jta="true" jndi-name="java:/jdbc/crmDS" pool-name="stockmarketDS" enabled="true" use-ccm="true">
    <connection-url>jdbc:mysql://localhost:3306/crm</connection-url>
    <driver-class>com.mysql.jdbc.Driver</driver-class>
    <driver>mysql</driver>
    <pool>
        <min-pool-size>2</min-pool-size>
        <max-pool-size>10</max-pool-size>
        <prefill>false</prefill>
    </pool>
    <security>
        <user-name>root</user-name>
        <password>Secret_123</password>
    </security>
    <validation>
        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
        <background-validation>true</background-validation>
        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
    </validation>
</datasource>
 */
@Entity
@Table(name="customers")
public class CustomerEntity {
	@Id
	private String id;
	@Column(name="full_name")
	private String fullname;
	private String email;
	@Embedded
	private List<PhoneEntity> phones;
	@Embedded
	private List<AddressEntity> addresses;
	public CustomerEntity() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<PhoneEntity> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneEntity> phones) {
		this.phones = phones;
	}
	public List<AddressEntity> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerEntity other = (CustomerEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CustomerEntity [id=" + id + ", fullname=" + fullname + ", email=" + email + ", phones=" + phones
				+ ", addresses=" + addresses + "]";
	}
	
}
