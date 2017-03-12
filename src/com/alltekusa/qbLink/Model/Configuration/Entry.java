package com.alltekusa.qbLink.Model.Configuration;

public class Entry {

	String name;//name of the entry
	String type;//datatype
	boolean create;//entry used for model creation
	boolean update;//entry used for model update
	String field="";//???
	String content="";//corresponding value mapped to quickbooks

	Entry() {

	}

	public Entry(String name, String type, boolean create, boolean update,String value) {
		super();
		this.name = name;
		this.type = type;
		this.create = create;
		this.update = update;
		this.content = value;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public boolean isCreate() {
		return create;
	}

	public void setCreate(boolean create) {
		this.create = create;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public String toString() {
		String toString = "<entry name='" + name + "' type='" + type
				+ "' create='"+create+"' update='"+update+"'>"+field+"</entry>";

		return toString;
	}
	public String toStringField(){
		String toString = "<entry name='" + name + "'>"+field+"</entry>";
		return toString;
	}
	public String toStringContent(){
		String toString = "<entry name='" + name + "'>"+content+"</entry>";
		return toString;
	}

	

}
