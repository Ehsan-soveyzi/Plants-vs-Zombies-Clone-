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

        private ImageView imageView = new ImageView();


        Plant(int cost, int hp,int row, int col) {
    //        , double x, double y
            this.cost = cost;
            this.hp = hp;
            this.row = row;
            this.col = col;
            this.isDead = false;
    //        this.x = x;
    //        this.y = y;
            setupImage();
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

        public abstract void setupImage();

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

    class PeaPlant extends Plant {

        PeaPlant(int cost, int hp,int row, int col) {
            super(cost, hp, row, col);
        }

        @Override
        public void update(double deltaTime) {
            if(this.isDead()){
                this.clearDiedPlant();
            }
        }

        @Override
        public void setupImage() {
            Image firstFrame = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/resources/graphics/Plants/Peashooter/Peashooter_0.png")));
            ImageView imageView = this.getImageView();
            imageView.setImage(firstFrame);
            this.playAnimation();
        }

        public void playAnimation() {
            Image[] frames = new Image[12];
            for(int i = 0;i < 12;i++){
                frames[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/resources/graphics/Plants/Peashooter/Peashooter_" + i + ".png")));
            }
            final int[] index = {0};
            ImageView peaShooter = getImageView();

            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100),e ->{
                this.update(0.1);
                peaShooter.setImage(frames[index[0]]);
                index[0] = (index[0] + 1) % frames.length;
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }

        //every time this method called a bullet object will be created!
        // باید در بازی بررسی بشه که در سطر تا زمانی که زامبی هست صدا زده بشه
        public void shoot() {

        }

    }

    //abstract class NutPlant extends Plant {
    //    // میتونیم با خود کلاس نوشت
    //    NutPlant(int cost, int hp, double x, double y) {
    //        super(cost, hp, x, y);
    //    }
    //
    //}
    //class WallNut extends NutPlant {
    //    WallNut(double x, double y) {
    //        super(50, 10, x, y);
    //    }
    //
    //    public void update(double deltaTime) {
    //        //no movement
    //    }
    //
    //    public void setupImage() {
    //        //set the unique image here
    //    }
    //}
    //
    //abstract class BombPlant extends Plant {
    //    BombPlant(int cost, int hp, double x, double y) {
    //        super(cost,hp, x, y);
    //    }
    //}


    //abstract class SunFlower extends Plant {
    //    //
    //    SunFlower(int cost, int hp, double x, double y) {
    //        super(cost, hp, x, y);
    //    }
    //}
