package com.cognizant.ormlearn.model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "customer_review")
    private double customerReview;

    @Column(name = "hard_disk_size")
    private String hardDiskSize;

    @Column(name = "ram_size")
    private String ramSize;

    @Column(name = "cpu_speed")
    private String cpuSpeed;

    @Column(name = "operating_system")
    private String operatingSystem;

    private double weight;
    private String cpu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCustomerReview() {
        return customerReview;
    }

    public void setCustomerReview(double customerReview) {
        this.customerReview = customerReview;
    }

    public String getHardDiskSize() {
        return hardDiskSize;
    }

    public void setHardDiskSize(String hardDiskSize) {
        this.hardDiskSize = hardDiskSize;
    }

    public String getRamSize() {
        return ramSize;
    }

    public void setRamSize(String ramSize) {
        this.ramSize = ramSize;
    }

    public String getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(String cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', ram=" + ramSize
                + ", os='" + operatingSystem + "', review=" + customerReview + "}";
    }
}
