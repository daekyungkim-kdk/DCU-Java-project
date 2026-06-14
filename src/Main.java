//포켓몬 4세대 신오도감 프로그램
package project;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 1. 포켓몬 기본 클래스

class Pokemon {
    protected int number;      // 도감 번호
    protected String name;     // 이름
    protected String[] types;  // 타입 배열

    public Pokemon(int number, String name, String type) {
        this.number = number;
        this.name = name;
        this.types = new String[]{type.trim()};
    }

    public Pokemon(int number, String name, String type1, String type2) {
        this.number = number;
        this.name = name;
        this.types = new String[]{type1.trim(), type2.trim()};
    }

    public String getName() { return name; }
    public int getNumber() { return number; }
    public String[] getTypes() { return types; }

    // 타입 포함 여부 확인
    public boolean hasType(String searchType) {
        String cleanSearch = searchType.trim();
        for (String t : types) {
            if (t.equalsIgnoreCase(cleanSearch)) {
                return true;
            }
        }
        return false;
    }

    // 포켓몬 정보 출력 형태 설정
    public String getInfo() {
        String typeInfo = String.join("/", types);
        return String.format(" No.%03d %s [%s 타입]", number, name, typeInfo);
    }
}

// 2. 메인 실행 클래스
public class Main extends JFrame {

    private JTextArea displayArea;
    private JTextField inputField;
    private List<Pokemon> sinnohDex;

    public Main() {
        sinnohDex = new ArrayList<>();

        // 4세대 신오 도감 데이터 삽입
        sinnohDex.add(new Pokemon(1, "모부기", "풀"));
        sinnohDex.add(new Pokemon(2, "수풀부기", "풀"));
        sinnohDex.add(new Pokemon(3, "토대부기", "풀", "땅"));
        sinnohDex.add(new Pokemon(4, "불꽃숭이", "불꽃"));
        sinnohDex.add(new Pokemon(5, "파이숭이", "불꽃", "격투"));
        sinnohDex.add(new Pokemon(6, "초염몽", "불꽃", "격투"));
        sinnohDex.add(new Pokemon(7, "팽도리", "물"));
        sinnohDex.add(new Pokemon(8, "팽태자", "물"));
        sinnohDex.add(new Pokemon(9, "엠페르트", "물", "강철"));
        sinnohDex.add(new Pokemon(10, "찌르꼬", "노말", "비행"));
        sinnohDex.add(new Pokemon(11, "찌르버드", "노말", "비행"));
        sinnohDex.add(new Pokemon(12, "찌르호크", "노말", "비행"));
        sinnohDex.add(new Pokemon(13, "비버니", "노말"));
        sinnohDex.add(new Pokemon(14, "비버통", "노말", "물"));
        sinnohDex.add(new Pokemon(15, "귀뚤뚜기", "벌레"));
        sinnohDex.add(new Pokemon(16, "귀뚤톡크", "벌레"));
        sinnohDex.add(new Pokemon(17, "꼬링크", "전기"));
        sinnohDex.add(new Pokemon(18, "럭시오", "전기"));
        sinnohDex.add(new Pokemon(19, "렌트라", "전기"));
        sinnohDex.add(new Pokemon(20, "캐이시", "에스퍼"));
        sinnohDex.add(new Pokemon(21, "윤겔라", "에스퍼"));
        sinnohDex.add(new Pokemon(22, "후딘", "에스퍼"));
        sinnohDex.add(new Pokemon(23, "잉어킹", "물"));
        sinnohDex.add(new Pokemon(24, "갸라도스", "물", "비행"));
        sinnohDex.add(new Pokemon(25, "꼬몽울", "풀", "독"));
        sinnohDex.add(new Pokemon(26, "로젤리아", "풀", "독"));
        sinnohDex.add(new Pokemon(27, "로즈레이드", "풀", "독"));
        sinnohDex.add(new Pokemon(28, "주뱃", "독", "비행"));
        sinnohDex.add(new Pokemon(29, "골뱃", "독", "비행"));
        sinnohDex.add(new Pokemon(30, "크로뱃", "독", "비행"));
        sinnohDex.add(new Pokemon(31, "꼬마돌", "바위", "땅"));
        sinnohDex.add(new Pokemon(32, "데구리", "바위", "땅"));
        sinnohDex.add(new Pokemon(33, "딱구리", "바위", "땅"));
        sinnohDex.add(new Pokemon(34, "롱스톤", "바위", "땅"));
        sinnohDex.add(new Pokemon(35, "강철톤", "강철", "땅"));
        sinnohDex.add(new Pokemon(36, "두개도스", "바위"));
        sinnohDex.add(new Pokemon(37, "램펄드", "바위"));
        sinnohDex.add(new Pokemon(38, "방패톱스", "바위", "강철"));
        sinnohDex.add(new Pokemon(39, "바리톱스", "바위", "강철"));
        sinnohDex.add(new Pokemon(40, "알통몬", "격투"));
        sinnohDex.add(new Pokemon(41, "근육몬", "격투"));
        sinnohDex.add(new Pokemon(42, "괴력몬", "격투"));
        sinnohDex.add(new Pokemon(43, "고라파덕", "물"));
        sinnohDex.add(new Pokemon(44, "골덕", "물"));
        sinnohDex.add(new Pokemon(45, "도롱충이", "벌레"));
        sinnohDex.add(new Pokemon(46, "도롱마담", "벌레", "풀"));
        sinnohDex.add(new Pokemon(47, "나메일", "벌레", "비행"));
        sinnohDex.add(new Pokemon(48, "개무소", "벌레"));
        sinnohDex.add(new Pokemon(49, "실쿤", "벌레"));
        sinnohDex.add(new Pokemon(50, "뷰티플라이", "벌레", "비행"));
        sinnohDex.add(new Pokemon(51, "카스쿤", "벌레"));
        sinnohDex.add(new Pokemon(52, "독케일", "벌레", "독"));
        sinnohDex.add(new Pokemon(53, "세꿀버리", "벌레", "비행"));
        sinnohDex.add(new Pokemon(54, "비퀸", "벌레", "비행"));
        sinnohDex.add(new Pokemon(55, "파치리스", "전기"));
        sinnohDex.add(new Pokemon(56, "브이젤", "물"));
        sinnohDex.add(new Pokemon(57, "플로젤", "물"));
        sinnohDex.add(new Pokemon(58, "체리버", "풀"));
        sinnohDex.add(new Pokemon(59, "체리꼬", "풀"));
        sinnohDex.add(new Pokemon(60, "껍질무", "물"));
        sinnohDex.add(new Pokemon(61, "트리토돈", "물", "땅"));
        sinnohDex.add(new Pokemon(62, "헤라크로스", "벌레", "격투"));
        sinnohDex.add(new Pokemon(63, "에이팜", "노말"));
        sinnohDex.add(new Pokemon(64, "겟핸보숭", "노말"));
        sinnohDex.add(new Pokemon(65, "흔들풍손", "고스트", "비행"));
        sinnohDex.add(new Pokemon(66, "둥실라이드", "고스트", "비행"));
        sinnohDex.add(new Pokemon(67, "이어롤", "노말"));
        sinnohDex.add(new Pokemon(68, "이어롭", "노말"));
        sinnohDex.add(new Pokemon(69, "고오스", "고스트", "독"));
        sinnohDex.add(new Pokemon(70, "고우스트", "고스트", "독"));
        sinnohDex.add(new Pokemon(71, "팬텀", "고스트", "독"));
        sinnohDex.add(new Pokemon(72, "무우마", "고스트"));
        sinnohDex.add(new Pokemon(73, "무우마직", "고스트"));
        sinnohDex.add(new Pokemon(74, "니로우", "악", "비행"));
        sinnohDex.add(new Pokemon(75, "돈크로우", "악", "비행"));
        sinnohDex.add(new Pokemon(76, "나옹마", "노말"));
        sinnohDex.add(new Pokemon(77, "몬냥이", "노말"));
        sinnohDex.add(new Pokemon(78, "콘치", "물"));
        sinnohDex.add(new Pokemon(79, "왕콘치", "물"));
        sinnohDex.add(new Pokemon(80, "미꾸리", "물", "땅"));
        sinnohDex.add(new Pokemon(81, "메깅", "물", "땅"));
        sinnohDex.add(new Pokemon(82, "랑딸랑", "에스퍼"));
        sinnohDex.add(new Pokemon(83, "치렁", "에스퍼"));
        sinnohDex.add(new Pokemon(84, "스컹뿡", "독", "악"));
        sinnohDex.add(new Pokemon(85, "스컹탱크", "독", "악"));
        sinnohDex.add(new Pokemon(86, "요가랑", "격투", "에스퍼"));
        sinnohDex.add(new Pokemon(87, "요가램", "격투", "에스퍼"));
        sinnohDex.add(new Pokemon(88, "동미러", "강철", "에스퍼"));
        sinnohDex.add(new Pokemon(89, "동탁군", "강철", "에스퍼"));
        sinnohDex.add(new Pokemon(90, "포니타", "불꽃"));
        sinnohDex.add(new Pokemon(91, "날쌩마", "불꽃"));
        sinnohDex.add(new Pokemon(92, "꼬지지", "바위"));
        sinnohDex.add(new Pokemon(93, "꼬지모", "바위"));
        sinnohDex.add(new Pokemon(94, "흉내내", "에스퍼"));
        sinnohDex.add(new Pokemon(95, "마임맨", "에스퍼"));
        sinnohDex.add(new Pokemon(96, "핑복", "노말"));
        sinnohDex.add(new Pokemon(97, "럭키", "노말"));
        sinnohDex.add(new Pokemon(98, "해피너스", "노말"));
        sinnohDex.add(new Pokemon(99, "삐", "노말"));
        sinnohDex.add(new Pokemon(100, "삐삐", "노말"));
        sinnohDex.add(new Pokemon(101, "픽시", "노말"));
        sinnohDex.add(new Pokemon(102, "페라페", "노말", "비행"));
        sinnohDex.add(new Pokemon(103, "피츄", "전기"));
        sinnohDex.add(new Pokemon(104, "피카츄", "전기"));
        sinnohDex.add(new Pokemon(105, "라이츄", "전기"));
        sinnohDex.add(new Pokemon(106, "부우부", "노말", "비행"));
        sinnohDex.add(new Pokemon(107, "야부엉", "노말", "비행"));
        sinnohDex.add(new Pokemon(108, "화강돌", "고스트", "악"));
        sinnohDex.add(new Pokemon(109, "딥상어동", "드래곤", "땅"));
        sinnohDex.add(new Pokemon(110, "한바이트", "드래곤", "땅"));
        sinnohDex.add(new Pokemon(111, "한카리아스", "드래곤", "땅"));
        sinnohDex.add(new Pokemon(112, "먹고자", "노말"));
        sinnohDex.add(new Pokemon(113, "잠만보", "노말"));
        sinnohDex.add(new Pokemon(114, "안농", "에스퍼"));
        sinnohDex.add(new Pokemon(115, "리오르", "격투"));
        sinnohDex.add(new Pokemon(116, "루카리오", "격투", "강철"));
        sinnohDex.add(new Pokemon(117, "우파", "물", "땅"));
        sinnohDex.add(new Pokemon(118, "누오", "물", "땅"));
        sinnohDex.add(new Pokemon(119, "갈모매", "물", "비행"));
        sinnohDex.add(new Pokemon(120, "패리퍼", "물", "비행"));
        sinnohDex.add(new Pokemon(121, "키링키", "노말", "에스퍼"));
        sinnohDex.add(new Pokemon(122, "히포포타스", "땅"));
        sinnohDex.add(new Pokemon(123, "하마돈", "땅"));
        sinnohDex.add(new Pokemon(124, "루리리", "노말"));
        sinnohDex.add(new Pokemon(125, "마릴", "물"));
        sinnohDex.add(new Pokemon(126, "마릴리", "물"));
        sinnohDex.add(new Pokemon(127, "스콜피", "독", "벌레"));
        sinnohDex.add(new Pokemon(128, "드래피온", "독", "악"));
        sinnohDex.add(new Pokemon(129, "삐딱구리", "독", "격투"));
        sinnohDex.add(new Pokemon(130, "독개굴", "독", "격투"));
        sinnohDex.add(new Pokemon(131, "무스틈니", "풀"));
        sinnohDex.add(new Pokemon(132, "총어", "물"));
        sinnohDex.add(new Pokemon(133, "대포무노", "물"));
        sinnohDex.add(new Pokemon(134, "형광어", "물"));
        sinnohDex.add(new Pokemon(135, "네오라이트", "물"));
        sinnohDex.add(new Pokemon(136, "왕눈해", "물", "독"));
        sinnohDex.add(new Pokemon(137, "독파리", "물", "독"));
        sinnohDex.add(new Pokemon(138, "빈티나", "물"));
        sinnohDex.add(new Pokemon(139, "밀로틱", "물"));
        sinnohDex.add(new Pokemon(140, "타만타", "물", "비행"));
        sinnohDex.add(new Pokemon(141, "만타인", "물", "비행"));
        sinnohDex.add(new Pokemon(142, "눈쓰개", "풀", "얼음"));
        sinnohDex.add(new Pokemon(143, "눈설왕", "풀", "얼음"));
        sinnohDex.add(new Pokemon(144, "포푸니", "악", "얼음"));
        sinnohDex.add(new Pokemon(145, "포푸니라", "악", "얼음"));
        sinnohDex.add(new Pokemon(146, "유크시", "에스퍼"));
        sinnohDex.add(new Pokemon(147, "엠라이트", "에스퍼"));
        sinnohDex.add(new Pokemon(148, "아그놈", "에스퍼"));
        sinnohDex.add(new Pokemon(149, "디아루가", "강철", "드래곤"));
        sinnohDex.add(new Pokemon(150, "펄기아", "물", "드래곤"));
        sinnohDex.add(new Pokemon(151, "마나피", "물"));
        sinnohDex.add(new Pokemon(152, "로토무", "전기", "고스트"));
        sinnohDex.add(new Pokemon(153, "글라이거", "땅", "비행"));
        sinnohDex.add(new Pokemon(154, "글라이온", "땅", "비행"));
        sinnohDex.add(new Pokemon(155, "코코파스", "바위"));
        sinnohDex.add(new Pokemon(156, "대코파스", "바위", "강철"));
        sinnohDex.add(new Pokemon(157, "랄토스", "에스퍼"));
        sinnohDex.add(new Pokemon(158, "킬리아", "에스퍼"));
        sinnohDex.add(new Pokemon(159, "가디안", "에스퍼"));
        sinnohDex.add(new Pokemon(160, "엘레이드", "에스퍼", "격투"));
        sinnohDex.add(new Pokemon(161, "내루미", "노말"));
        sinnohDex.add(new Pokemon(162, "내룸벨트", "노말"));
        sinnohDex.add(new Pokemon(163, "이브이", "노말"));
        sinnohDex.add(new Pokemon(164, "샤미드", "물"));
        sinnohDex.add(new Pokemon(165, "쥬피썬더", "전기"));
        sinnohDex.add(new Pokemon(166, "부스터", "불꽃"));
        sinnohDex.add(new Pokemon(167, "에브이", "에스퍼"));
        sinnohDex.add(new Pokemon(168, "블랙키", "악"));
        sinnohDex.add(new Pokemon(169, "리피아", "풀"));
        sinnohDex.add(new Pokemon(170, "글레이시아", "얼음"));
        sinnohDex.add(new Pokemon(171, "파비코", "노말", "비행"));
        sinnohDex.add(new Pokemon(172, "파비코리", "드래곤", "비행"));
        sinnohDex.add(new Pokemon(173, "토게피", "노말"));
        sinnohDex.add(new Pokemon(174, "토게틱", "노말", "비행"));
        sinnohDex.add(new Pokemon(175, "토게키스", "노말", "비행"));
        sinnohDex.add(new Pokemon(176, "델빌", "악", "불꽃"));
        sinnohDex.add(new Pokemon(177, "헬가", "악", "불꽃"));
        sinnohDex.add(new Pokemon(178, "코일", "전기", "강철"));
        sinnohDex.add(new Pokemon(179, "레어코일", "전기", "강철"));
        sinnohDex.add(new Pokemon(180, "자포코일", "전기", "강철"));
        sinnohDex.add(new Pokemon(181, "덩쿠리", "풀"));
        sinnohDex.add(new Pokemon(182, "덩쿠림보", "풀"));
        sinnohDex.add(new Pokemon(183, "왕자리", "벌레", "비행"));
        sinnohDex.add(new Pokemon(184, "메가자리", "벌레", "비행"));
        sinnohDex.add(new Pokemon(185, "트로피우스", "풀", "비행"));
        sinnohDex.add(new Pokemon(186, "뿔카노", "땅", "바위"));
        sinnohDex.add(new Pokemon(187, "코뿌리", "땅", "바위"));
        sinnohDex.add(new Pokemon(188, "거대코뿌리", "땅", "바위"));
        sinnohDex.add(new Pokemon(189, "해골몽", "고스트"));
        sinnohDex.add(new Pokemon(190, "미라몽", "고스트"));
        sinnohDex.add(new Pokemon(191, "야느와르몽", "고스트"));
        sinnohDex.add(new Pokemon(192, "폴리곤", "노말"));
        sinnohDex.add(new Pokemon(193, "폴리곤2", "노말"));
        sinnohDex.add(new Pokemon(194, "폴리곤Z", "노말"));
        sinnohDex.add(new Pokemon(195, "스라크", "벌레", "비행"));
        sinnohDex.add(new Pokemon(196, "핫삼", "벌레", "강철"));
        sinnohDex.add(new Pokemon(197, "에레키드", "전기"));
        sinnohDex.add(new Pokemon(198, "에레브", "전기"));
        sinnohDex.add(new Pokemon(199, "에레키블", "전기"));
        sinnohDex.add(new Pokemon(200, "마그비", "불꽃"));
        sinnohDex.add(new Pokemon(201, "마그마", "불꽃"));
        sinnohDex.add(new Pokemon(202, "마그마번", "불꽃"));
        sinnohDex.add(new Pokemon(203, "꾸꾸리", "얼음", "땅"));
        sinnohDex.add(new Pokemon(204, "메꾸리", "얼음", "땅"));
        sinnohDex.add(new Pokemon(205, "맘모꾸리", "얼음", "땅"));
        sinnohDex.add(new Pokemon(206, "눈꼬마", "얼음"));
        sinnohDex.add(new Pokemon(207, "얼음귀신", "얼음"));
        sinnohDex.add(new Pokemon(208, "눈여아", "얼음", "고스트"));
        sinnohDex.add(new Pokemon(209, "앱솔", "악"));
        sinnohDex.add(new Pokemon(210, "기라티나", "고스트", "드래곤"));

        // GUI 추가됨
        setTitle("✨ 4세대 포켓몬 신오도감 ✨");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 창에 전체적인 여백
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout(10, 10));

        Image icon = new ImageIcon(getClass().getResource("/PokeBall.jpg")).getImage();
        setIconImage(icon);

        JPanel topPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        JPanel inputPanel = new JPanel(new BorderLayout(7, 7));
        inputPanel.add(new JLabel(" 👉 검색(타입 또는 이름) 입력: "), BorderLayout.WEST);
        inputField = new JTextField();
        inputPanel.add(inputField, BorderLayout.CENTER);
        topPanel.add(inputPanel);

        JPanel examplePanel = new JPanel(new GridLayout(2, 1));
        JLabel typeEx = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;💡 타입 : "
                + "<font color='#F0F0D0'>노말</font> "
                + "<font color='#FF8080'>격투</font> "
                + "<font color='#D0C0FF'>비행</font> "
                + "<font color='#FF90FF'>독</font> "
                + "<font color='#FFE090'>땅</font> "
                + "<font color='#E0D080'>바위</font> "
                + "<font color='#D8E070'>벌레</font> "
                + "<font color='#D0B0FF'>고스트</font> "
                + "<font color='#E0E0F0'>강철</font> "
                + "<font color='#FFA070'>불꽃</font> "
                + "<font color='#90C0FF'>물</font> "
                + "<font color='#A0E090'>풀</font> "
                + "<font color='#FFF070'>전기</font> "
                + "<font color='#FF80A0'>에스퍼</font> "
                + "<font color='#B0FFFF'>얼음</font> "
                + "<font color='#A080FF'>드래곤</font> "
                + "<font color='#B09080'>악</font></html>");
        JLabel nameEx = new JLabel("      💡 포켓몬 예시 : 팽도리, 불꽃숭이");

        examplePanel.add(typeEx);
        examplePanel.add(nameEx);
        examplePanel.setOpaque(false);
        topPanel.add(examplePanel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnAll = new JButton("1. 전체 도감");
        JButton btnType = new JButton("2. 타입 검색");
        JButton btnName = new JButton("3. 이름 검색");
        JButton btnSave = new JButton("4. TXT파일로 저장");

        buttonPanel.add(btnAll);
        buttonPanel.add(btnType);
        buttonPanel.add(btnName);
        buttonPanel.add(btnSave);
        topPanel.add(buttonPanel);

        topPanel.setBackground(new Color(220, 53, 69));
        inputPanel.setOpaque(false); // 배경색이 잘 보이게 투명 처리
        buttonPanel.setOpaque(false);

        add(topPanel, BorderLayout.NORTH);

        // 결과 출력창
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        displayArea.setBackground(new Color(245, 248, 250));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);
        // 화면 UI


        // 1번: 전체 도감 보기
        btnAll.addActionListener(e -> {
            displayArea.setText("\n📜 [4세대 신오 도감 전체 목록]\n");
            for (Pokemon p : sinnohDex) {
                displayArea.append(p.getInfo() + "\n");
            }
            displayArea.setCaretPosition(0);
        });

        // 2번: 특정 타입별 포켓몬 나열하기
        btnType.addActionListener(e -> {
            String searchType = inputField.getText().trim();

            if (searchType.isEmpty()) {
                JOptionPane.showMessageDialog(this, "타입/포켓몬 이름을 입력해주세요!! \n (타입 예:불꽃, 땅) \n (포켓몬 예:팽도리,불꽃숭이)", "입력 오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 입력한 타입이 존재하는 타입인지 확인
            if (!searchType.equals("노말") && !searchType.equals("불꽃") && !searchType.equals("물") &&
                    !searchType.equals("풀") && !searchType.equals("전기") && !searchType.equals("얼음") &&
                    !searchType.equals("격투") && !searchType.equals("독") && !searchType.equals("땅") &&
                    !searchType.equals("비행") && !searchType.equals("에스퍼") && !searchType.equals("벌레") &&
                    !searchType.equals("바위") && !searchType.equals("고스트") && !searchType.equals("드래곤") &&
                    !searchType.equals("강철") && !searchType.equals("악")) {

                JOptionPane.showMessageDialog(this, "존재하지 않는 포켓몬 타입입니다!", "입력 오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            displayArea.setText("\n🎯 [" + searchType + " 타입 포켓몬 목록]\n");
            int count = 0;
            for (Pokemon p : sinnohDex) {
                if (p.hasType(searchType)) {
                    displayArea.append(p.getInfo() + "\n");
                    count++;
                }
            }
            displayArea.append("총 " + count + "마리가 검색되었습니다.\n");
            displayArea.setCaretPosition(0);
        });

        // 3번: 포켓몬 이름으로 검색하기 🔍
        // 이름으로 개별 검색 기능 구현 완료
        btnName.addActionListener(e -> {
            String searchName = inputField.getText().trim();
            boolean found = false;

            for (Pokemon p : sinnohDex) {
                // 이름 일치하는지 비교
                if (p.getName().equalsIgnoreCase(searchName)) {
                    displayArea.setText("\n🔎 [검색 결과 발견!]\n");
                    displayArea.append("================================\n");
                    displayArea.append(p.getInfo() + "\n");
                    displayArea.append("================================\n");
                    found = true;
                    break;
                }
            }
            if (!found) {
                displayArea.setText("❌ 오류: '" + searchName + "'은(는) 도감에 등록되지 않은 포켓몬입니다.\n");
            }
        });

        // 4번: 검색한 타입 리스트 파일로 저장하기
        btnSave.addActionListener(e -> {
            String fileType = inputField.getText().trim();

            // 파일 저장 시에도 잘못된 타입 차단
            if (!fileType.equals("노말") && !fileType.equals("불꽃") && !fileType.equals("물") &&
                    !fileType.equals("풀") && !fileType.equals("전기") && !fileType.equals("얼음") &&
                    !fileType.equals("격투") && !fileType.equals("독") && !fileType.equals("땅") &&
                    !fileType.equals("비행") && !fileType.equals("에스퍼") && !fileType.equals("벌레") &&
                    !fileType.equals("바위") && !fileType.equals("고스트") && !fileType.equals("드래곤") &&
                    !fileType.equals("강철") && !fileType.equals("악")) {

                displayArea.setText("❌ 오류: '" + fileType + "'은(는) 존재하지 않는 포켓몬 타입이므로 파일을 생성할 수 없습니다.\n");
                return;
            }

            String fileName = fileType + "_type_pokemon.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write("====== " + fileType + " 타입 4세대 포켓몬 리스트 ======\n");

                int fileCount = 0;
                for (Pokemon p : sinnohDex) {
                    if (p.hasType(fileType)) {
                        writer.write(p.getInfo() + "\n");
                        fileCount++;
                    }
                }
                writer.write("======================================\n");
                writer.write("총 " + fileCount + "마리의 포켓몬이 기록되었습니다.\n");

                displayArea.setText("💾 '" + fileName + "' 파일이 성공적으로 생성되었습니다!\n");
            } catch (IOException ex) {
                displayArea.setText("❌ 파일 저장 중 오류가 발생했습니다: " + ex.getMessage() + "\n");
            }
        });
    }

    public static void main(String[] args) {
        // ui 꾸미기
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true); // 창을 화면에 띄움
        });
    }
}