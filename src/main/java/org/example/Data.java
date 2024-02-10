package org.example;

public class Data {
    private int year;
    private double price;
    private String cpuModel;
    private String hardDiskSize;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Data data)) return false;
        return
                getYear() == data.getYear() &&
                getPrice() == data.getPrice() &&
                getCpuModel().equals(data.getCpuModel()) &&
                getHardDiskSize().equals(data.getHardDiskSize());
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }

    public String getHardDiskSize() {
        return hardDiskSize;
    }

    public void setHardDiskSize(String hardDiskSize) {
        this.hardDiskSize = hardDiskSize;
    }
}
