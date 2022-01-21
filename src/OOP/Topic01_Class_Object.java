package OOP;

import java.util.Scanner;

public class Topic01_Class_Object {
    private int maSinhVien;
    private String hoTen;
    private float diemLyThuyet, diemThucHanh, total;

    public void setMaSinhVien(int maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setDiemLyThuyet(float diemLyThuyet) {
        this.diemLyThuyet = diemLyThuyet;
    }

    public void setDiemThucHanh(float diemThucHanh) {
        this.diemThucHanh = diemThucHanh;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getMaSinhVien() {
        return maSinhVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public float getDiemLyThuyet() {
        return diemLyThuyet;
    }

    public float getDiemThucHanh() {
        return diemThucHanh;
    }

    public float getTotal() {
        return total;
    }

    public void nhapData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ma sinh vien: ");
        maSinhVien = scanner.nextInt();
        System.out.print("Nhap ho va ten sinh vien: ");
        hoTen = scanner.next();
        System.out.print("Nhap diem ly thuyet: ");
        diemLyThuyet = scanner.nextFloat();
        System.out.print("Nhap diem thuc hanh: ");
        diemThucHanh = scanner.nextFloat();

        System.out.println("ma sinh vien: " + maSinhVien);
        System.out.println("ho ten sinh vien: " + hoTen);
        System.out.println("diem ly thuyet: " + diemLyThuyet);
        System.out.println("diem thuc hanh: " + diemThucHanh);

    }

    public void xuLyData() {
        total = (diemLyThuyet + diemThucHanh*2)/3;
        System.out.println("tong diem: " + total);
    }

    public static void main(String[] args) {
        System.out.println("Sinh vien 1");
        Topic01_Class_Object sinhVien1 = new Topic01_Class_Object();
        sinhVien1.nhapData();
        sinhVien1.xuLyData();
        System.out.println("Sinh vien 2");
        Topic01_Class_Object sinhVien2 = new Topic01_Class_Object();
        sinhVien2.nhapData();
        sinhVien2.xuLyData();

    }
}
