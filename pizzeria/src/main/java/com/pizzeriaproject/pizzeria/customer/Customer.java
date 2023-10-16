package com.pizzeriaproject.pizzeria.customer;


import jakarta.persistence.*;

@Entity(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_seq", allocationSize = 1)
    @Column(name = "id_", unique = true, updatable = false)
    private Long id;
    @Column(name = "user_name_", unique = true)
    private String username;
    @Column(name = "password_")
    private String password;
    @Column(name = "email_", unique = true)
    private String email;
    @Column(name = "name_")
    private String name;
    @Column(name = "surname_")
    private String surname;
    @Column(name = "phone_number_")
    private String phoneNumber;
    @Column(name = "address_")
    private String address;

    protected Customer () {

    }
    protected Customer(Long id, String username, String password, String email, String name, String surname, String phoneNumber, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    protected Customer(String username, String password, String email, String name, String surname, String phoneNumber, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
