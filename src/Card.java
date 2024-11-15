/**
 * カードクラス
 * - モンスターカードの情報を管理
 * - 名前、攻撃力(AP)、防御力(DP)を保持
 */
public class Card {
    private String name; // モンスターの名前
    private int ap;      // 攻撃力
    private int dp;      // 防御力

    // コンストラクタ
    public Card(String name, int ap, int dp) {
        this.name = name;
        this.ap = ap;
        this.dp = dp;
    }

    // 名前を取得
    public String getName() {
        return name;
    }

    // 攻撃力を取得
    public int getAp() {
        return ap;
    }

    // 防御力を取得
    public int getDp() {
        return dp;
    }
}
