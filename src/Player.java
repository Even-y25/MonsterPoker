import java.util.ArrayList;
import java.util.List;

/**
 * プレイヤークラス
 * - プレイヤーまたはCPUの状態を管理
 * - 手札（カードのリスト）、HP、攻撃・防御倍率を保持
 */
public class Player {
    private List<Card> deck;       // プレイヤーの手札
    private double hp;             // プレイヤーのHP
    private double apMultiplier = 1; // 攻撃力の倍率
    private double dpMultiplier = 1; // 防御力の倍率

    // コンストラクタ：初期HPを設定
    public Player(double initialHp) {
        this.hp = initialHp;
        this.deck = new ArrayList<>();
    }

    // 手札を取得
    public List<Card> getDeck() {
        return deck;
    }

    // HPを取得
    public double getHp() {
        return hp;
    }

    // HPを設定
    public void setHp(double hp) {
        this.hp = hp;
    }

    // 攻撃力の倍率を取得
    public double getApMultiplier() {
        return apMultiplier;
    }

    // 攻撃力の倍率を設定
    public void setApMultiplier(double multiplier) {
        this.apMultiplier = multiplier;
    }

    // 防御力の倍率を取得
    public double getDpMultiplier() {
        return dpMultiplier;
    }

    // 防御力の倍率を設定
    public void setDpMultiplier(double multiplier) {
        this.dpMultiplier = multiplier;
    }
}
