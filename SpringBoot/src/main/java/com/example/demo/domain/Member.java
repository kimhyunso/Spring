package com.example.demo.domain;

import com.example.demo.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@SuppressWarnings("Lombok")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "member")
@Builder(builderMethodName = "MemberBuilder")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long memberId;

    @Column(name = "email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="name")
    private String name;
    @Column(name="phoneNum")
    private String phoneNum;


    @Column(name="createAt")
    @CreationTimestamp
    private LocalDateTime createAt = LocalDateTime.now();


    @Column(name="modifyAt")
    @UpdateTimestamp
    private LocalDateTime modifyAt = LocalDateTime.now();

    @Column(name="ip")
    private String ip;

    // PersistentBag ==> List
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<AttachFile> attachFiles = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();


    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<MemberRole>();

    public void addMemberRole(MemberRole memberRole){
        roleSet.add(memberRole);
    }


    public void convertToDomain(MemberDTO memberDTO){
        this.setMemberId(memberDTO.getMemberId());
        this.setIp(memberDTO.getIp());
        this.setName(memberDTO.getName());
        this.setEmail(memberDTO.getEmail());
        this.setPhoneNum(memberDTO.getPhoneNum());
    }

}
