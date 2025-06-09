    package Character;

    import javafx.animation.KeyFrame;
    import javafx.animation.Timeline;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.util.Duration;

    import java.util.Objects;
    import java.util.Timer;

    public abstract class Plant {


        private int cost;
        private int hp;
        private double cooldown;
        private double x, y;
        private boolean isDead;
        private int row,col;

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public void setCol(int col) {
            this.col = col;
        }

        private ImageView imageView;


        Plant(int cost, int hp,int row, int col) {
            this.cost = cost;
            this.hp = hp;
            this.row = row;
            this.col = col;
            this.isDead = false;
        }
        public void takeDamage() {
            if (isDead) return;
            hp--;
            if (hp <= 0) die();
        }

        public void die() {
            isDead = true;
            clearDiedPlant();
        }

        public void clearDiedPlant(){ // با مختصات گرید

        }

        public abstract void update(double deltaTime);


        public int getCost() {return cost;}
        public void setCost(int cost) {this.cost = cost;}
        public double getHp() {return hp;}
        public void setHp(int hp) {this.hp = hp;}
        public double getX() {return x;}
        public void setX(double x) {this.x = x;}
        public double getY() {return y;}
        public void setY(double y) {this.y = y;}
        public double getCooldown() {return cooldown;}
        public void setCooldown(double cooldown) {this.cooldown = cooldown;}
        public boolean isDead() {
            return isDead;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setDead(boolean dead) {
            isDead = dead;
        }
    }
