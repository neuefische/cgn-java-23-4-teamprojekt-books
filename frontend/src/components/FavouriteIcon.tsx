import React from "react";

type FavouriteIconProps = {
  isActive: boolean;
};

export const FavouriteIcon: React.FC<FavouriteIconProps> = ({ isActive }) => {
  return (
    <svg
      width="40px"
      height="40px"
      viewBox="0 0 24 24"
      fill={isActive ? "#DC4A4A" : "none"}
      xmlns="http://www.w3.org/2000/svg"
    >
      <g id="SVGRepo_bgCarrier" strokeWidth="0" />

      <g id="SVGRepo_tracerCarrier" strokeLinecap="round" strokeLinejoin="round" />

      <g id="SVGRepo_iconCarrier">
        <path
          d="M4.42602 12.3115L12 19.8854L19.574 12.3115C21.4753 10.4101 21.4753 7.32738 19.574 5.42602C17.6726 3.52466 14.5899 3.52466 12.6885 5.42602L12 6.11456L11.3115 5.42602C9.4101 3.52466 6.32738 3.52466 4.42602 5.42602C2.52466 7.32738 2.52466 10.4101 4.42602 12.3115Z"
          stroke={isActive ? "#DC4A4A" : "#000000"}
          strokeWidth="1"
          strokeLinejoin="round"
        />
      </g>
    </svg>
  );
};
