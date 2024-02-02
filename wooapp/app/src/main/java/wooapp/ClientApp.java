/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package wooapp;

import java.sql.Connection;
import java.sql.DriverManager;
import wooapp.menu.MenuGroup;
import wooapp.myapp.dao.BoardDao;
import wooapp.myapp.dao.JJ_MemberDao;
import wooapp.myapp.dao.mysql.AssignmentDaoImpl;
import wooapp.myapp.dao.mysql.BoardDaoImpl;
import wooapp.myapp.dao.mysql.JJMemberDaoImpl;
import wooapp.myapp.handler.HelpHandler;
import wooapp.myapp.handler.board.BoardAddHandler;
import wooapp.myapp.handler.board.BoardDeleteHandler;
import wooapp.myapp.handler.board.BoardListHandler;
import wooapp.myapp.handler.board.BoardModifyHandler;
import wooapp.myapp.handler.board.BoardViewHandler;
import wooapp.util.Prompt;

public class ClientApp {
    Prompt prompt = new Prompt(System.in);

    BoardDao boardDao;
    BoardDao greetingDao;
    wooapp.myapp.dao.AssignmentDao assignmentDao;
    JJ_MemberDao JJMemberDao;

    MenuGroup mainMenu;

    ClientApp() {
        prepareDatabase();
        prepareMenu();
    }

    public static void main(String[] args) {
        System.out.println("[주짓수 관리 시스템]");
        new wooapp.ClientApp().run();
    }

    void prepareDatabase() {
        try {
            // JVM이 JDBC 드라이버 파일(.jar)에 설정된대로 자동으로 처리한다.
//      Driver driver = new com.mysql.cj.jdbc.Driver();
//      DriverManager.registerDriver(driver);

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://db-ld28n-kr.vpc-pub-cdb.ntruss.com/studydb", "study",
                "Bitcamp!@#123");
//      "jdbc:mysql://localhost/studydb", "study", "Bitcamp!@#123");

            boardDao = new BoardDaoImpl(con, 1);
            greetingDao = new BoardDaoImpl(con, 2);
            assignmentDao = new AssignmentDaoImpl(con);
            JJMemberDao = new JJMemberDaoImpl(con);

        } catch (Exception e) {
            System.out.println("통신 오류!");
            e.printStackTrace();
        }
    }

    void prepareMenu() {
        mainMenu = MenuGroup.getInstance("메인");

        MenuGroup assignmentMenu = mainMenu.addGroup("과제");
        assignmentMenu.addItem("등록", new wooapp.myapp.handler.assignment.AssignmentAddHandler(assignmentDao, prompt));
        assignmentMenu.addItem("조회", new wooapp.myapp.handler.assignment.AssignmentViewHandler(assignmentDao, prompt));
        assignmentMenu.addItem("변경", new wooapp.myapp.handler.assignment.AssignmentModifyHandler(assignmentDao, prompt));
        assignmentMenu.addItem("삭제", new wooapp.myapp.handler.assignment.AssignmentDeleteHandler(assignmentDao, prompt));
        assignmentMenu.addItem("목록", new wooapp.myapp.handler.assignment.AssignmentListHandler(assignmentDao, prompt));

        MenuGroup boardMenu = mainMenu.addGroup("게시글");
        boardMenu.addItem("등록", new BoardAddHandler(boardDao, prompt));
        boardMenu.addItem("조회", new BoardViewHandler(boardDao, prompt));
        boardMenu.addItem("변경", new BoardModifyHandler(boardDao, prompt));
        boardMenu.addItem("삭제", new BoardDeleteHandler(boardDao, prompt));
        boardMenu.addItem("목록", new BoardListHandler(boardDao, prompt));

        MenuGroup memberMenu = mainMenu.addGroup("JJ회원");
        memberMenu.addItem("등록", new wooapp.myapp.handler.member.JJ_MemberAddHandler(JJMemberDao, prompt));
        memberMenu.addItem("조회", new wooapp.myapp.handler.member.JJ_MemberViewHandler(JJMemberDao, prompt));
        memberMenu.addItem("변경", new wooapp.myapp.handler.member.JJ_MemberModifyHandler(JJMemberDao, prompt));
        memberMenu.addItem("삭제", new wooapp.myapp.handler.member.JJ_MemberDeleteHandler(JJMemberDao, prompt));
        memberMenu.addItem("목록", new wooapp.myapp.handler.member.JJ_MemberListHandler(JJMemberDao, prompt));

        MenuGroup greetingMenu = mainMenu.addGroup("가입인사");
        greetingMenu.addItem("등록", new BoardAddHandler(greetingDao, prompt));
        greetingMenu.addItem("조회", new BoardViewHandler(greetingDao, prompt));
        greetingMenu.addItem("변경", new BoardModifyHandler(greetingDao, prompt));
        greetingMenu.addItem("삭제", new BoardDeleteHandler(greetingDao, prompt));
        greetingMenu.addItem("목록", new BoardListHandler(greetingDao, prompt));

        mainMenu.addItem("도움말", new HelpHandler(prompt));
    }

    void run() {
        while (true) {
            try {
                mainMenu.execute(prompt);
                prompt.close();
                break;
            } catch (Exception e) {
                System.out.println("예외 발생!");
            }
        }
    }

}