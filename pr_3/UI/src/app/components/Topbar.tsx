import { ReactNode } from "react";

interface TopbarProps {
  title: string;
  leftAction?: ReactNode;
  rightAction?: ReactNode;
}

export default function Topbar({ title, leftAction, rightAction }: TopbarProps) {
  return (
    <div
      className="sticky top-0 z-30 flex items-center justify-between px-4 py-3 backdrop-blur-[14px] border-b border-[rgba(255,255,255,0.10)]"
      style={{
        background: 'rgba(18, 22, 30, 0.78)'
      }}
    >
      <div className="flex-1 flex items-center">
        {leftAction}
      </div>
      <h1 className="text-[rgba(216,220,230,0.92)] font-[800] tracking-[0.12em] uppercase text-xs">
        {title}
      </h1>
      <div className="flex-1 flex items-center justify-end">
        {rightAction}
      </div>
    </div>
  );
}
