import { useRef, useCallback, useEffect } from 'react';

const useIntersect = (onIntersect, options) => {
  const ref = useRef(null);

  // intersectionObserver 인자로 사용할 콜백작성
  const callback = useCallback(
    (entries, observer) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) onIntersect(entry, observer);
        console.log('entrie');
      });
    },
    [onIntersect],
  );

  useEffect(() => {
    // ref.current 가 지정되지 않은 경우 예외처리
    if (!ref.current) return {};

    // 위에서 작성한 함수를 intersectionObserver 의 콜백으로 등록
    const observer = new IntersectionObserver(callback, options);
    observer.observe(ref.current);
    console.log('connet observer');

    // 언마운트될때 옵저버연결 끊기
    return () => observer.disconnect();
  }, [ref]);

  return ref;
};

export default useIntersect;
