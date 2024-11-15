import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * MonsterPokerゲームクラス
 * - ゲームの主要ロジックを管理
 * - ドローフェーズ、バトルフェーズ、勝敗判定を含む
 */
public class MonsterPoker {
    private Random random = new Random();          // ランダム生成用
    private List<Card> monsterCards;              // モンスターカードのリスト
    private Player player;                        // プレイヤー
    private Player cpu;                           // CPU

    // コンストラクタ：モンスターカードとプレイヤーを初期化
    public MonsterPoker() {
        this.monsterCards = Arrays.asList(
            new Card("スライム", 10, 40),
            new Card("サハギン", 20, 20),
            new Card("ドラゴン", 30, 25),
            new Card("デュラハン", 25, 15),
            new Card("シーサーペント", 30, 20)
        );
        this.player = new Player(1000); // プレイヤーの初期HP
        this.cpu = new Player(1000);    // CPUの初期HP
    }

    /**
     * ドローフェーズ
     * - プレイヤーとCPUがカードを引く
     * - プレイヤーはカード交換が可能
     */
    public void drawPhase(Scanner scanner) {
        System.out.println("PlayerのDraw！");
        drawCards(player);
        displayDeck(player.getDeck(), "Player");

        System.out.println("カードを交換する場合は1から5の数字を続けて入力してください．交換しない場合は0を入力してください");
        String input = scanner.nextLine();
        exchangeCards(player, input);

        System.out.println("CPUのDraw！");
        drawCards(cpu);
        displayDeck(cpu.getDeck(), "CPU");

        System.out.println("CPUが交換するカードを考えています...");
        exchangeCards(cpu, null);
    }

    // カードを引く
    private void drawCards(Player player) {
        List<Card> deck = player.getDeck();
        deck.clear();
        for (int i = 0; i < 5; i++) {
            deck.add(monsterCards.get(random.nextInt(monsterCards.size())));
        }
    }

    // デッキを表示
    private void displayDeck(List<Card> deck, String playerType) {
        System.out.printf("[%s] ", playerType);
        deck.forEach(card -> System.out.printf("%s ", card.getName()));
        System.out.println();
    }

    // カードを交換
    private void exchangeCards(Player player, String input) {
        List<Card> deck = player.getDeck();
        if (input == null) { // CPUの交換ロジック
            for (int i = 0; i < deck.size(); i++) {
                if (random.nextBoolean()) {
                    deck.set(i, monsterCards.get(random.nextInt(monsterCards.size())));
                }
            }
        } else { // プレイヤーの交換ロジック
            if (!input.equals("0")) {
                for (char c : input.toCharArray()) {
                    int index = Character.getNumericValue(c) - 1;
                    if (index >= 0 && index < deck.size()) {
                        deck.set(index, monsterCards.get(random.nextInt(monsterCards.size())));
                    }
                }
            }
        }
    }

    /**
     * バトルフェーズ
     * - プレイヤーとCPUの攻撃と防御を計算
     */
    public void battlePhase() {
        System.out.println("バトル開始！");
        double playerDamage = calculateDamage(player.getDeck());
        double cpuDamage = calculateDamage(cpu.getDeck());

        cpu.setHp(cpu.getHp() - Math.max(playerDamage - calculateDefense(cpu.getDeck()), 0));
        player.setHp(player.getHp() - Math.max(cpuDamage - calculateDefense(player.getDeck()), 0));

        System.out.printf("PlayerのHP: %.1f\n", player.getHp());
        System.out.printf("CPUのHP: %.1f\n", cpu.getHp());
    }

    // 攻撃力を計算
    private double calculateDamage(List<Card> deck) {
        return deck.stream().mapToDouble(Card::getAp).sum();
    }

    // 防御力を計算
    private double calculateDefense(List<Card> deck) {
        return deck.stream().mapToDouble(Card::getDp).sum();
    }

    // ゲーム終了条件を確認
    public boolean isGameOver() {
        return player.getHp() <= 0 || cpu.getHp() <= 0;
    }

    // 勝敗結果を表示
    public void showWinner() {
        if (player.getHp() <= 0 && cpu.getHp() <= 0) {
            System.out.println("引き分け！");
        } else if (player.getHp() <= 0) {
            System.out.println("CPUの勝利！");
        } else {
            System.out.println("Playerの勝利！");
        }
    }
}
