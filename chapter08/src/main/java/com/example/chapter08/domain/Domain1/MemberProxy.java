package com.example.chapter08.domain.Domain1;

public class MemberProxy extends Member{

    Member target = null;

    public String getName(){
        if (target == null){

            // 2. 초기화요청
            // 3. DB조회
            // 4. 실제 엔티티 생성 및 참조보관
            // target = 4값;
        }
        // 5. target.getName();
        return target.getUsername();
    }
}
