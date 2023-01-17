package org.example;

import java.util.List;
import java.util.Scanner;

public class MemberManageApp {

    private static final Scanner scanner = new Scanner(System.in);

    public static void appStart(){
        System.out.println(
                "==============================\n" +
                        " Ham's_tory memberManageApp\n" +
                "============START=============\n"     );
    }
    public static void appEnd(){
        System.out.println(
                "============END=============\n" +
                        "  Ham's_tory memberManageApp\n" +
                        "==============================\n");
    }
    public static void dispMenu(){
        while(true){
            System.out.println("==============================\n" +
                    "[1] 회원 전체조회\n" +
                    "[2] 회원 검색\n" +
                    "[3] 회원 추가\n" +
                    "[4] 회원정보 변경\n" +
                    "[5] 회원정보 삭제\n" +
                    "==============================\n" +
                    "[0] 프로그램 종료\n" +
                    "==============================");
            int choice = scanner.nextInt();
            switch (choice){
                case 0: return;
                case 1: getMemberList();continue;
                case 2: getMember();continue;
                case 3: addMember();continue;
                case 4: updateMember();continue;
                case 5: deleteMember();continue;
                default:
                    System.out.println("해당 숫자가 아닙니다. 다시 입력해주세요");
            }
        }
    }

    public static void getMemberList(){
        MemberDAO memberDAO = new MemberDAO();
        List<Member> memberList = memberDAO.selectALL();

        if(memberList.isEmpty()){
            System.out.println("등록된 회원이 없습니다.");
        }else{
            System.out.println(memberList);
        }

    }

    public static void getMember(){
        MemberDAO memberDAO = new MemberDAO();
        System.out.println("ID를 입력해주세요.");
        String id = scanner.next();
        Member member = memberDAO.select(id);

        if(member==null){
            System.out.println("존재하지 않는 회원입니다.");
        }else{
            System.out.println(member);
        }

    }

    public static void addMember(){
        MemberDAO memberDAO = new MemberDAO();
        System.out.println("ID를 입력해주세요: ");
        String id = scanner.next();
        if(id.equals("")){
            System.out.println("ID는 필수항목입니다.");
        }else if(memberDAO.select(id)!=null) {
            System.out.println("해당 ID는 이미 존재합니다.");
        }else{
            System.out.println("이름을 입력해주세요.");
            String name = scanner.next();
            if(name==null||name.equals("")){
                System.out.println("이름은 필수항목입니다.");
            }else{
                System.out.println("Email을 입력해주세요.");
                String email = scanner.next();
                if(email==null||email.equals("")){
                    System.out.println("Email은 필수항목입니다.");
                }else{
                    System.out.println("핸드폰번호를 입력해주세요.");
                    String phone = scanner.next();
                    if(phone==null||phone.equals("")) {
                        System.out.println("핸드폰번호는 필수항목입니다.");
                    }else{
                        memberDAO.insert(new Member(id,name,email,phone));
                        System.out.println("등록되었습니다.");
                    }
                }
            }
        }

    }

    public static void updateMember(){
        MemberDAO memberDAO = new MemberDAO();
        System.out.println("정보 변경하실 ID를 입력해주세요.");
        String id = scanner.next();
        Member member = memberDAO.select(id);

        if(member==null){
            System.out.println("존재하지 않는 회원입니다.");
        }else if(id.equals("")){
            System.out.println("ID를 입력은 필수입니다.");
        }else{
            System.out.println("변경하실 핸드폰번호를 입력해주세요");
            String nPhone = scanner.next();
            member.setPhone(nPhone);
            memberDAO.update(member);
            System.out.println("변경이 완료되었습니다.");
        }

    }
    public static void deleteMember(){
        MemberDAO memberDAO = new MemberDAO();
        System.out.println("삭제하실 ID를 입력해주세요.");
        String id = scanner.next();
        Member member = memberDAO.select(id);

        if(member==null) {
            System.out.println("존재하지 않는 회원입니다.");
        }else if(id.equals("")){
            System.out.println("ID를 입력은 필수입니다.");
        }else{
            memberDAO.delete(member);
            System.out.println("삭제가 완료되었습니다.");

            }
        }

    }







