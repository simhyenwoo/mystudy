package bitcamp.menu;

import bitcamp.util.Prompt;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class MenuGroup extends AbstractMenu {

    private List<Menu> menus = new LinkedList<>();

    private MenuGroup(String title) {
        super(title);
    }


    public static MenuGroup getInstance(String title) {
        return new MenuGroup(title);
    }

    @Override // 인터페이스나 수퍼 클래스의 메서드를 정의하겠다고 컴파일러에게 알린다.
    public void execute(Prompt prompt) throws Exception {

        prompt.pushPath(this.title);

        this.printMenu(prompt);

        while (true) {

            String input = prompt.input("%s>", prompt.getFullPath());

            if (input.equals("menu")) {
                this.printMenu(prompt);
                continue;
            } else if (input.equals("0")) {
                break;
            }

            try {
                int menuNo = Integer.parseInt(input);
                if (menuNo < 1 || menuNo > this.menus.size()) {
                    System.out.println("메뉴 번호가 옳지 않습니다.");
                    continue;
                }

                this.menus.get(menuNo - 1).execute(prompt);


            } catch (Exception e) {
                System.out.println("메뉴가 옳지 않습니다!");
            }
        }
        prompt.popPath();
    }

    private void printMenu(Prompt prompt) {
        prompt.printf("[%s]\n", this.getTitle());

        Iterator<Menu> iterator = this.menus.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            Menu menu = iterator.next();
            prompt.printf("%d. %s\n", i++, menu.getTitle());
        }

        prompt.printf("0. %s\n", "이전");
    }

    public void add(Menu menu) {
        this.menus.add(menu);
    }

    public MenuItem addItem(String title, MenuHandler handler) {
        MenuItem menuItem = new MenuItem(title, handler);
        this.add(menuItem);
        return menuItem;
    }

    public MenuGroup addGroup(String title) {
        MenuGroup menuGroup = new MenuGroup(title);
        this.add(menuGroup);
        return menuGroup;
    }

    public void remove(Menu menu) {
        this.menus.remove(menu);
    }
}
