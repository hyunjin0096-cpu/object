# View 쪽에서 맞춰줘야 하는 getter / update 메서드 목록

아래 이름은 `KioskController.java`에서 호출하는 이름입니다.  
View 담당자가 이미 만든 메서드명이 다르면, 둘 중 하나만 맞추면 됩니다.

## MainFrame
- `showPanel(String panelName)`

## MainPanel
- `getNextBtn()`

## MenuPanel
- `getVanillaBtn()`
- `getStrawberryBtn()`
- `getChocoBtn()`
- `getNextBtn()`
- `getBackBtn()`

## OptionPanel
- `getCupBtn()`
- `getConeBtn()`
- `getChocoChipCheck()`
- `getHoneyCheck()`
- `getCerealCheck()`
- `getNextBtn()`
- `getBackBtn()`

## CartPanel
- `getPaymentBtn()`
- `getDeleteBtn()`
- `getClearBtn()`
- `getAddMoreBtn()`
- `getSelectedItemIndex()`
- `updateCartList(List<OrderItem> items)`
- `updateTotalPrice(int totalPrice)`

## OrderCompletePanel
- `getHomeBtn()`
- `setOrderNumber(int orderNumber)`
- `setTotalPrice(int totalPrice)`
