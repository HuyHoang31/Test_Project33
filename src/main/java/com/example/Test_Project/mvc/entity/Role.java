//package com.example.Test_Project.mvc.entity;
//
//public enum Role {
//    ADMIN(1), USER(2);
//
//    private final int id;
//
//    Role(int id) {
//        this.id = id;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public static Role fromId(int id) {
//        for (Role role : Role.values()) {
//            if (role.getId() == id) {
//                return role;
//            }
//        }
//        throw new IllegalArgumentException("Invalid role ID: " + id);
//    }
//}
