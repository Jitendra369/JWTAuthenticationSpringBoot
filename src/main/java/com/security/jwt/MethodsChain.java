package com.security.jwt;

public class MethodsChain {
	
	private String name;
	private int age;
	
	public MethodsChain() {	}
	
	
	public MethodsChain setName(String name) {
		this.name = name;
		return this;
	}
	
	public MethodsChain setAge(int age) {
		this.age = age;
		return this;
	}
	
	public MethodsChain display() {
		System.out.println("Name is :"+ this.name + " Age is :"+ this.age);
		return this;
	}
	

}
