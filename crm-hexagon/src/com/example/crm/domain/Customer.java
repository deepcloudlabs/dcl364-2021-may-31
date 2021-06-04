package com.example.crm.domain;

import static java.util.stream.Collectors.toList;

import java.util.List;

// DDD : Sub-domain -> Bounded Context -> Ubiquitous Language -> Model
// DDD : Entity -> Persistent, i) Identity ii) Mutable
//       i) Entity ii) Value Object iii) Entity Root -> Aggregate
//       Aggregate -> Sub-domain -> BC -> Consistent
//       Invariants, Pre-/Post-Conditions, Business Rule, ...
//       Aggregate -> Transaction Boundary
@DomainEntity(identity = "kimlikNo")
@Aggregate
public class Customer {
	private TcKimlikNo kimlikNo;
	private FullName fullname;
	private Email email;
	private List<Phone> phones;
	private List<Address> addresses;

	private Customer(Builder builder) {
		this.kimlikNo = builder.kimlikNo;
		this.fullname = builder.fullname;
		this.email = builder.email;
		this.phones = builder.phones;
		this.addresses = builder.addresses;
	}

	public static class Builder {
		private TcKimlikNo kimlikNo;
		private FullName fullname;
		private Email email;
		private List<Phone> phones;
		private List<Address> addresses;

		public Builder(TcKimlikNo kimlikNo) {
			this.kimlikNo = kimlikNo;
		}

		public Builder fullname(String first, String last) {
			this.fullname = FullName.of(first, last);
			return this;
		}

		public Builder email(String value) {
			this.email = Email.valueOf(value);
			return this;
		}

		public Builder phones(List<String> values) {
			this.phones = values.stream().map(Phone::of).collect(toList());
			return this;
		}

		public Builder addresses(List<String> values) {
			this.addresses = values.stream().map(Address::valueOf).collect(toList());
			return this;
		}

		public Customer build() {
			// validation/business rule/invariants/... -> consistency
			return new Customer(this);
		}
	}

	public TcKimlikNo getKimlikNo() {
		return kimlikNo;
	}

	public void setKimlikNo(TcKimlikNo kimlikNo) {
		this.kimlikNo = kimlikNo;
	}

	public FullName getFullname() {
		return fullname;
	}

	public void setFullname(FullName fullname) {
		this.fullname = fullname;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
