# 🍦 28조 지능형 키오스크 - View 브랜치 가이드 및 변경사항

본 브랜치(`View`)는 오현진 담당의 **키오스크 화면 UI 컴포넌트 설계 및 화면 전환 흐름**이 구현된 브랜치입니다. 팀원들(시은, 연우)이 이 브랜치를 기반으로 Model 데이터와 Controller 기능을 부드럽게 연결할 수 있도록 표준 규격을 맞추어 작성되었습니다.

---

## 🛠️ 핵심 변경 사항 (회의 피드백 반영)

1. **Model 명세 변수 규격화 (송시은)**
   - **Menu**: `choco`, `vanilla`, `strawberry` (기본 가격: 3,500원)
   - **ServingOption**: `cup` / `cone` (콘 선택 시 +500원 추가)
   - **ToppingOption**: `chocochip` (+1,000원), `honey` (+500원), `cereal` (+1,000원)

2. **Controller 연동 표준 규격화 (조연우)**
   - View와 Controller의 결합도를 낮추고 이벤트를 쉽게 바인딩할 수 있도록, 모든 화면 패널의 메인 버튼(다음 단계 진입 버튼)을 **`public JButton getNextBtn()`** 메서드로 통일하여 제공합니다.

3. **서비스 플로우 간소화 (프로젝트 안정성 확보)**
   - 기존 주제선정보고서의 복잡한 결제 프로세스(`PaymentPanel`)는 런타임 에러 및 스레드 꼬임 방지를 위해 제외했습니다.
   - **[장바구니 화면(`CartPanel`)] ➡️ [주문 완료 화면(`OrderCompletePanel`)]**으로 다이렉트 전환되도록 동선을 깔끔하게 수정했습니다.

---

## 📂 파일 구성 (총 7개 파일)

모든 파일은 `src` 폴더 내부에서 서로 유기적으로 결합되어 작동합니다.

### 📺 View 영역 (오현진)
- `MainFrame.java`: 전체 프로그램의 프레임 틀을 제공하는 최상위 컨테이너 (`CardLayout` 적용)
- `MainPanel.java`: 주문 시작을 유도하는 첫 번째 터치 화면 (시작 화면)
- `MenuPanel.java`: 아이스크림 플레이버(`choco`, `vanilla`, `strawberry`) 선택 화면
- `OptionPanel.java`: 라디오 버튼(용기) 및 체크박스(토핑 다중 선택) 커스텀 화면
- `CartPanel.java`: 선택 내역을 `JTable` 표 형태로 정돈하고 총 금액을 출력하는 장바구니 화면 (문법 에러 수정 완료)
- `OrderCompletePanel.java`: 실시간 주문 번호를 대형 폰트로 출력하는 최종 완료 화면

### 🎮 Controller & Launcher 영역
- `KioskController.java`: 모든 View 패널의 `getNextBtn()`을 가져와 화면 전환 흐름을 통제하는 리모컨 뼈대 코드
- `KioskLauncher.java`: 모든 부품을 조립하고 프로그램을 실행하는 메인(main) 메서드 파일

---

## 🤝 팀원 협업 체크리스트 (Next Step)

### 🎮 To. 조연우 (Controller)
- `KioskController`에 화면 전환 흐름과 데이터 수집 뼈대 코드를 미리 심어두었습니다. `KioskLauncher`를 실행하면 화면이 정상적으로 순환하는 것을 확인할 수 있으니, 이 구조 위에 시은이의 Model 객체를 연결하여 데이터를 주고받는 상세 로직을 구현해 주세요.

### 🧱 To. 송시은 (Model)
- 장바구니(`CartPanel`) 화면에 영수증을 띄울 수 있도록 `JTable`과 `DefaultTableModel`을 선언해 두었습니다. `Cart` 클래스에서 담긴 아이템 리스트를 반환할 때, 테이블 모델에 행 (`tableModel.addRow()`)을 순차적으로 추가할 수 있게 데이터 규격을 연우와 함께 맞춰주세요.

---

## 🚀 실행 및 테스트 방법
1. 원격 저장소에서 최신 상태를 반영합니다: `git fetch origin`
2. View 브랜치로 전환합니다: `git checkout View`
3. IDE(VS Code / Eclipse / IntelliJ)에서 **`KioskLauncher.java`** 파일을 열고 **Run**을 실행하면 전체 UI의 작동 흐름을 바로 테스트해 볼 수 있습니다.
