package com.logapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogApiApplication {

	public static void main(String[] args) {

		A a = new B();
		B b = new B();

		System.out.println(a);
		System.out.println(b);
	}

	public class B extends A {
		public String toString(){
			return super.toString() + getClass().getSimpleName();
		}
	}

	public class A {
		public String toString() {
			return getClass().getSimpleName();
		}
	}
}
