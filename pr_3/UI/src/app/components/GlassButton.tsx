import { ReactNode } from "react";

interface GlassButtonProps {
  children: ReactNode;
  onClick?: () => void;
  variant?: "default" | "danger" | "accent";
  className?: string;
  type?: "button" | "submit";
}

export default function GlassButton({
  children,
  onClick,
  variant = "default",
  className = "",
  type = "button"
}: GlassButtonProps) {
  const variants = {
    default: "border-[rgba(255,255,255,0.14)] hover:border-[rgba(255,255,255,0.28)]",
    danger: "border-[rgba(184,74,82,0.4)] hover:border-[rgba(184,74,82,0.6)] text-[#b84a52]",
    accent: "border-[rgba(126,200,240,0.4)] hover:border-[rgba(126,200,240,0.6)] text-[#7ec8f0]"
  };

  return (
    <button
      type={type}
      onClick={onClick}
      className={`
        rounded-full px-4 py-2
        bg-[rgba(0,0,0,0.22)] border
        text-[rgba(232,233,240,0.92)]
        shadow-[inset_0_1px_0_rgba(255,255,255,0.05)]
        transition-all duration-150
        hover:bg-[rgba(0,0,0,0.32)]
        active:translate-y-[1px] active:scale-[0.98]
        ${variants[variant]}
        ${className}
      `}
    >
      {children}
    </button>
  );
}
