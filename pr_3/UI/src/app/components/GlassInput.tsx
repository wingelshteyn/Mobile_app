import { InputHTMLAttributes } from "react";

interface GlassInputProps extends InputHTMLAttributes<HTMLInputElement> {
  icon?: React.ReactNode;
}

export default function GlassInput({ icon, className = "", ...props }: GlassInputProps) {
  return (
    <div className="relative">
      {icon && (
        <div className="absolute left-3 top-1/2 -translate-y-1/2 text-[#9a9eac]">
          {icon}
        </div>
      )}
      <input
        {...props}
        className={`
          w-full rounded-[10px] px-3 py-2.5
          ${icon ? 'pl-10' : ''}
          bg-[rgba(0,0,0,0.36)]
          border border-[rgba(255,255,255,0.10)]
          text-[#e8e9f0] placeholder:text-[#9a9eac]
          transition-all duration-150
          focus:outline-none focus:outline-2 focus:outline-dashed focus:outline-[rgba(180,190,210,0.55)]
          focus:bg-[rgba(255,255,255,0.07)]
          ${className}
        `}
      />
    </div>
  );
}
