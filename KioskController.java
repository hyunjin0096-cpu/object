// KioskController.java (총대가 새로 만들 파일)
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KioskController implements ActionListener{
    // 1. 팀원들이 각자 만든 파일(클래스)들을 여기에 다 불러모읍니다.
    private MainFrame mainFrame;
    private MenuPanel menuPanel;
    private OptionPanel optionPanel;
    private Order modelOrder; // Model 데이터

    // 2. 생성자에서 이것들을 한 번에 다 받아서 연결 통로를 뚫습니다.
    public KioskController(MainFrame mainFrame, MenuPanel menuPanel, OptionPanel optionPanel, Order modelOrder){
        this.mainFrame = mainFrame;
        this.menuPanel = menuPanel;
        this.optionPanel = optionPanel;
        this.modelOrder = modelOrder;

        // [★중요] View 담당자가 만들어둔 버튼에 "너 클릭되면 나(컨트롤러)한테 신호 보내!"라고 등록하는 과정
        this.menuPanel.getNextButton().addActionListener(this);
    }

    // 3. 버튼이 클릭되면 무조건 이 메서드로 신호가 들어옵니다 (교재 9장 이벤트 처리)
    @Override
    public void actionPerformed(ActionEvent e){
        // 만약 메뉴화면의 'Next' 버튼이 눌렸다면?
        if (e.getSource() == menuPanel.getNextButton()) {

            // ① Model 데이터 저장: 사용자가 선택한 맛을 Model에 넘김
            String selected = menuPanel.getSelectedFlavor(); // (View에서 가져옴)
            modelOrder.setFlavor(selected); // (Model에 저장함)

            // ② 화면 전환: 메인 프레임을 시켜서 다음 패널(OptionPanel)로 화면을 바꿈
            mainFrame.changePanel(optionPanel);
        }
    }
}
