package section01.insert;

import java.util.Scanner;

/**
 * Service 계층과 Repository 계층을 구분하고 XML 파일로부터
 * 쿼리를 불러와서 insert 해보기 위함.
 * */
public class Application2 {
    /*설명. view이자 controller 개념(하나의 Menu 객체로 만들기)인 main에서부터 시작*/
    public static void main(String[] args) {
        //목차. view
        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴 이름 입력: ");
        String menuName = sc.nextLine();
        System.out.print("메뉴 가격 입력: ");
        int menuPrice = sc.nextInt();
        System.out.print("카테고리 코드 입력: ");
        int categoryCode = sc.nextInt();
        System.out.print("판매 상태 입력(Y/N):");
        sc.nextLine();
        String orderableStatus = sc.nextLine();

        //목차. controller
        Menu registMenu = new Menu(menuName,menuPrice,categoryCode,orderableStatus);
        MenuService service = new MenuService();
        service.registMenu(registMenu);
    }
}
