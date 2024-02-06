package section02.update;

import java.util.Scanner;

/**
 * 목표<br><br>
 * 1. <br>
 */
public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("변경할 메뉴 번호 입력: ");
        int menuCode = sc.nextInt();
        System.out.print("변경할 메뉴 이름 입력: ");
        sc.nextLine();
        String menuName = sc.nextLine();
        System.out.print("변경할 메뉴 가격 입력: ");
        int menuPrice = sc.nextInt();

        Menu modifyMenu = new Menu(menuCode,menuName,menuPrice);
        MenuService service = new MenuService();
        service.modifyMenu(modifyMenu);
    }
}