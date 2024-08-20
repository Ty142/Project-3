
package com.javaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "assignmentbuilding")
public class AssignmentBuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity building;

    @ManyToOne
    @JoinColumn(name = "staffid")
    private UserEntity staffs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getStaffs() {
        return staffs;
    }

    public void setStaffs(UserEntity staffs) {
        this.staffs = staffs;
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }
}
