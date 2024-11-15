import java.util.Scanner;

/**
 * メインクラス
 * - ゲームのエントリーポイント
 * - ゲーム全体の進行を制御
 */
public class Main {
    public static void main(String[] args) {
        // ユーザー入力用のスキャナを作成
        Scanner scanner = new Scanner(System.in);

        // MonsterPokerゲームの初期化
        MonsterPoker game = new MonsterPoker();

        // ゲームのループ
        while (!game.isGameOver()) {
            // ドローフェーズ（カードを引く）
            game.drawPhase(scanner);
            // バトルフェーズ（戦闘）
            game.battlePhase();
        }

        // 勝敗結果の表示
        game.showWinner();

        // スキャナを閉じる
        scanner.close();
    }
}
