import { useEffect, useRef } from "react";
import fx from "./backgroundFx.module.css";

type Particle = { x: number; y: number; r: number; vx: number; vy: number; a: number };

function rand(min: number, max: number) {
  return Math.random() * (max - min) + min;
}

export default function BackgroundFx({ points = 280 }: { points?: number }) {
  const canvasRef = useRef<HTMLCanvasElement | null>(null);
  const rafRef = useRef<number | null>(null);
  const particlesRef = useRef<Particle[]>([]);

  useEffect(() => {
    const canvas = canvasRef.current;
    if (!canvas) return;

    const reduceMotion = window.matchMedia?.("(prefers-reduced-motion: reduce)")?.matches ?? false;
    if (reduceMotion) return;

    const ctx = canvas.getContext("2d");
    if (!ctx) return;

    let w = 0;
    let h = 0;
    let dpr = 1;
    const N = Math.max(0, Math.floor(points));

    function resize() {
      dpr = Math.max(1, Math.floor(window.devicePixelRatio || 1));
      w = canvas.clientWidth;
      h = canvas.clientHeight;
      canvas.width = Math.floor(w * dpr);
      canvas.height = Math.floor(h * dpr);
      ctx.setTransform(dpr, 0, 0, dpr, 0, 0);
    }

    function init() {
      particlesRef.current = Array.from({ length: N }, () => ({
        x: rand(0, w),
        y: rand(0, h),
        r: rand(1.1, 2.4),
        vx: rand(-0.12, 0.12),
        vy: rand(-0.09, 0.09),
        a: rand(0.08, 0.24)
      }));
    }

    function step() {
      ctx.clearRect(0, 0, w, h);

      const pts = particlesRef.current;
      for (const p of pts) {
        p.x += p.vx;
        p.y += p.vy;
        if (p.x < -20) p.x = w + 20;
        if (p.x > w + 20) p.x = -20;
        if (p.y < -20) p.y = h + 20;
        if (p.y > h + 20) p.y = -20;

        ctx.beginPath();
        ctx.fillStyle = `rgba(235,235,240,${p.a})`;
        ctx.arc(p.x, p.y, p.r, 0, Math.PI * 2);
        ctx.fill();
      }

      for (let i = 0; i < pts.length; i++) {
        for (let j = i + 1; j < pts.length; j++) {
          const a = pts[i];
          const b = pts[j];
          const dx = a.x - b.x;
          const dy = a.y - b.y;
          const dist = Math.sqrt(dx * dx + dy * dy);
          if (dist > 140) continue;
          const alpha = (1 - dist / 140) * 0.052;
          ctx.strokeStyle = `rgba(235,235,240,${alpha})`;
          ctx.lineWidth = 1;
          ctx.beginPath();
          ctx.moveTo(a.x, a.y);
          ctx.lineTo(b.x, b.y);
          ctx.stroke();
        }
      }

      rafRef.current = requestAnimationFrame(step);
    }

    resize();
    init();
    step();

    const onResize = () => {
      resize();
      init();
    };
    window.addEventListener("resize", onResize);

    return () => {
      window.removeEventListener("resize", onResize);
      if (rafRef.current != null) cancelAnimationFrame(rafRef.current);
      rafRef.current = null;
    };
  }, [points]);

  return (
    <div className={fx.bgWrap} aria-hidden>
      <div className={fx.bgOrbs}>
        <div className={[fx.bgOrb, fx.bgOrb1].join(" ")} />
        <div className={[fx.bgOrb, fx.bgOrb2].join(" ")} />
        <div className={[fx.bgOrb, fx.bgOrb3].join(" ")} />
        <div className={[fx.bgOrb, fx.bgOrb4].join(" ")} />
        <div className={[fx.bgOrb, fx.bgOrb5].join(" ")} />
      </div>
      <div className={fx.bgLines} />
      <canvas ref={canvasRef} className={fx.bgParticles} />
    </div>
  );
}
