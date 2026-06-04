import { ReactNode } from "react";

interface GlassCardProps {
  children: ReactNode;
  className?: string;
  onClick?: () => void;
}

export default function GlassCard({ children, className = "", onClick }: GlassCardProps) {
  return (
    <div
      onClick={onClick}
      className={`
        relative overflow-hidden rounded-[14px] p-3
        bg-[rgba(6,8,12,0.22)]
        border border-[rgba(255,255,255,0.12)]
        backdrop-blur-[18px] backdrop-saturate-[1.08]
        shadow-[0_14px_40px_rgba(0,0,0,0.35),0_0_28px_rgba(230,235,245,0.025),inset_0_1px_0_rgba(255,255,255,0.06)]
        transition-all duration-150
        ${onClick ? 'cursor-pointer hover:bg-[rgba(6,8,12,0.32)] hover:border-[rgba(255,255,255,0.22)] active:scale-[0.98]' : ''}
        ${className}
      `}
      style={{
        background: `
          linear-gradient(165deg, rgba(255,255,255,0.055) 0%, rgba(255,255,255,0.018) 48%, rgba(0,0,0,0.05) 100%),
          rgba(6,8,12,0.22)
        `
      }}
    >
      {children}
    </div>
  );
}
