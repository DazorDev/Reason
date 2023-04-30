package dazor.reason.framework;

public class Engine implements Reason {
	
	private boolean running = true;
	private long current, last, delta;
	
	private int interval;
	
	private Runnable logic;
	private Runnable render;
	
	@Override
	public void loop() {
		last = System.currentTimeMillis();
		while(running) {
			updateDelta();
			tick();
			sleep(interval-delta);
		}
	}
	
	@Override
	public void tick() {
		while(delta >= interval) {
			logic.run();
			delta -= interval;
		}
		render.run();
	}
	
	private void sleep(long time) {
		if(time <= 0) return;
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void updateDelta() {
		current = System.currentTimeMillis();
		delta += current - last;
		last = current;
	}

	@Override
	public long getDelta() {
		return delta;
	}

	@Override
	public void stop() {
		running = false;
	}

	@Override
	public void setDelta(long delta) {
		this.delta = delta;
	}
	
	@Override
	public void setIntervall(int interval) {
		this.interval = interval;
	}

	@Override
	public int getIntervall() {
		return interval;
	}

	@Override
	public void setLogic(Runnable logic) {
		this.logic = logic;
	}

	@Override
	public void setRender(Runnable render) {
		this.render = render;
	}
	
}
